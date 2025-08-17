package com.example.news

import com.example.util.Header.ACCEPT
import com.example.util.Header.APPLICATION_JSON
import com.example.util.call
import io.ktor.client.*
import io.ktor.http.*
import news.NewsClient
import news.NewsResponse
import news.Station
import news.StationUrlManager

class NewsClientImpl(
    private val httpClient: HttpClient,
    private val bbcXmlParser: XmlParser,
    private val newYorkTimesXmlParser: XmlParser,
) : NewsClient {
    override suspend fun request(station: Station): List<NewsResponse> {
        val url = StationUrlManager.getUrl(station)
        val response = httpClient.call<String>(
            url = url,
            httpMethod = HttpMethod.Get,
            headers = mapOf(ACCEPT to APPLICATION_JSON)
        )

        return when (station) {
            Station.BBC -> bbcXmlParser.parse(station, response)
            Station.NEW_YORK_TIMES -> newYorkTimesXmlParser.parse(station, response)
        }
    }
}