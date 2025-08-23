package com.example.router

import arrow.core.Either.Companion.catch
import arrow.core.getOrElse
import arrow.fx.coroutines.parMap
import com.example.datafetcher.datafetcher.ExchangeRateDataFetcher
import com.example.datafetcher.datafetcher.NewsDataFetcher
import com.example.datafetcher.datafetcher.WeatherDataFetcher
import com.example.model.FeedResponse
import com.example.util.extension.exclude
import exchangerate.Currency
import exchangerate.ExchangeRateResponse
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import news.Station
import org.koin.ktor.ext.inject
import weather.City
import weather.WeatherResponse

fun Route.feed() {
    val weatherDataFetcher by inject<WeatherDataFetcher>()
    val newsDataFetcher by inject<NewsDataFetcher>()
    val exchangeRateDataFetcher by inject<ExchangeRateDataFetcher>()

    get("/feed") {
        val feedResponse = coroutineScope {
            val weathersDeferred = async {
                City.entries.parMap(coroutineContext, concurrency = 10) { city ->
                    catch { weatherDataFetcher.fetch(city) }
                        .getOrElse { WeatherResponse.empty(city) }
                }
            }

            val newsDeferred = async {
                Station.entries.parMap(coroutineContext, concurrency = 2) { station ->
                    catch { newsDataFetcher.fetch(station) }
                        .getOrElse { emptyList() }
                }.flatten()
            }

            val exchangeRateDeferred = async {
                val currencies= Currency.entries.exclude(Currency.KRW)

                currencies.parMap(coroutineContext, concurrency = 5) { currency ->
                    catch { exchangeRateDataFetcher.fetch(to = Currency.KRW, from = currency) }
                        .getOrElse { ExchangeRateResponse.empty(to = Currency.KRW, from = currency) }
                }
            }

            val weathers = weathersDeferred.await()
            val news = newsDeferred.await()
            val exchangeRates = exchangeRateDeferred.await()

            FeedResponse.of(weathers, news, exchangeRates)
        }

        call.respond(HttpStatusCode.OK, feedResponse)
    }
}