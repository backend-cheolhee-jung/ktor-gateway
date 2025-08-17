package com.example.model

import weather.WeatherResponse

data class FeedResponse(
    val weatherResponses: List<WeatherResponse>,
) {
    companion object {
        fun of(weatherResponses: List<WeatherResponse>) =
            FeedResponse(weatherResponses)

        fun of(vararg weatherResponses: WeatherResponse) =
            FeedResponse(weatherResponses.toList())

        fun empty() = FeedResponse(emptyList())
    }
}
