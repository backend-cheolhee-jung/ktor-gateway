package com.example.datafetcher.datafetcher

import kotlinx.rpc.krpc.ktor.client.KtorRpcClient
import kotlinx.rpc.withService
import news.NewsClient
import news.NewsResponse
import news.Station

class NewsDataFetcher(
    private val ktorRpcClient: KtorRpcClient,
) {
    suspend fun fetch(station: Station): List<NewsResponse> {
        val newsClient: NewsClient = ktorRpcClient.withService<NewsClient>()
        return newsClient.request(station)
    }
}