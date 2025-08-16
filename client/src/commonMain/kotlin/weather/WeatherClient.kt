package weather

import io.ktor.client.*
import io.ktor.http.*
import util.Header.ACCEPT
import util.Header.APPLICATION_JSON
import util.call


class WeatherClientImpl : WeatherClient {
    override suspend fun request(city: City): WeatherResponse {
        val response = httpClient.call<String>(
            url = URL.replace("{city}", city.name),
            httpMethod = HttpMethod.Get,
            headers = mapOf(ACCEPT to APPLICATION_JSON)
        )

        val weatherToString = response.substringAfter(": ").substringBefore(" +")
        val temperature = response.substringAfter(" +").substringBefore("°C")

        return WeatherResponse.of(
            city = city,
            weather = Weather.fromEmoji(weatherToString),
            temperature = temperature.toDoubleOrNull() ?: 0.0
        )
    }

    private companion object {
        const val URL = "https://wttr.in/{city}/?format=3"
        val httpClient = HttpClient()
    }
}