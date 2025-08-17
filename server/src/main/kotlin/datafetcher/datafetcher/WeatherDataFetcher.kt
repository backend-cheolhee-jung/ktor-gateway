package com.example.datafetcher.datafetcher

import kotlinx.rpc.krpc.ktor.client.KtorRpcClient
import kotlinx.rpc.withService
import weather.City
import weather.WeatherClient
import weather.WeatherResponse

class WeatherDataFetcher(
    private val ktorRpcClient: KtorRpcClient,
) {
    suspend fun fetch(city: City): WeatherResponse {
        val weatherClient: WeatherClient = ktorRpcClient.withService<WeatherClient>()
        return weatherClient.request(city)
    }
}