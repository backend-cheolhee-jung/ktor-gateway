package com.example.model

import news.NewsResponse
import weather.WeatherResponse

data class FeedResponse(
    val weatherResponses: List<WeatherResponse>,
    val newsResponses: List<NewsResponse>,
) {
    companion object {
        fun of(
            weatherResponses: List<WeatherResponse>,
            newsResponses: List<NewsResponse>,
        ) =
            FeedResponse(weatherResponses, newsResponses)

        fun empty() = FeedResponse(emptyList(), emptyList())
    }
}
