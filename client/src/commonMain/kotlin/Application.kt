package com.example

import Environment
import Environment.RpcUrl.NEWS
import Environment.RpcUrl.WEATHER
import com.example.news.BbcXmlParser
import com.example.news.NewsClientImpl
import com.example.weather.WeatherClientImpl
import io.ktor.client.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json
import news.NewsClient
import weather.WeatherClient

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    install(Krpc)

    routing {
        url {
            host = Environment.HOST
            port = Environment.PORT
            encodedPath = Environment.ENCODED_PATH
        }

        rpc(WEATHER) {
            rpcConfig {
                serialization { json() }
            }

            registerService<WeatherClient>(::WeatherClientImpl)
        }
        rpc(NEWS) {
            rpcConfig {
                serialization { json() }
            }

            registerService<NewsClient> {
                NewsClientImpl(
                    httpClient = HttpClient(),
                    bbcXmlParser = BbcXmlParser(),
                    newYorkTimesXmlParser = BbcXmlParser(),
                )
            }
        }
    }
}