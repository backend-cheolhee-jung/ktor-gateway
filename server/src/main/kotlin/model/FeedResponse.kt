package com.example.model

import exchangerate.ExchangeRateResponse
import news.NewsResponse
import weather.WeatherResponse

data class FeedResponse(
    val weatherResponses: List<WeatherResponse>,
    val newsResponses: List<NewsResponse>,
    val exchangeRateResponses: List<ExchangeRateResponse>,
) {
    companion object {
        fun of(
            weatherResponses: List<WeatherResponse>,
            newsResponses: List<NewsResponse>,
            exchangeRateResponses: List<ExchangeRateResponse>,
        ) =
            FeedResponse(weatherResponses, newsResponses, exchangeRateResponses)

        fun empty() = FeedResponse(
            weatherResponses = emptyList(),
            newsResponses = emptyList(),
            exchangeRateResponses = emptyList(),
        )
    }
}
