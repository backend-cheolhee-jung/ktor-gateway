package com.example.router

import arrow.core.Either
import arrow.core.getOrElse
import arrow.fx.coroutines.parMap
import com.example.datafetcher.WeatherDataFetcher
import com.example.model.FeedResponse
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import weather.City
import weather.WeatherResponse

fun Route.feed() {
    val weatherDataFetcher by inject<WeatherDataFetcher>()

    get("/feed") {
        val weathers: List<WeatherResponse> = City.entries.parMap(concurrency = 10) { city ->
            Either.catch { weatherDataFetcher.fetch(city) }
                .getOrElse { WeatherResponse.empty(city) }
        }

        val response = FeedResponse.of(weathers)
        call.respond(HttpStatusCode.OK, response)
    }
}