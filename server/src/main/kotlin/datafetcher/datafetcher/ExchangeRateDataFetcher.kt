package com.example.datafetcher.datafetcher

import exchangerate.Currency
import exchangerate.ExchangeRateClient
import exchangerate.ExchangeRateResponse
import kotlinx.rpc.krpc.ktor.client.KtorRpcClient
import kotlinx.rpc.withService


class ExchangeRateDataFetcher(
    private val ktorRpcClient: KtorRpcClient,
) {
    suspend fun fetch(to: Currency, from: Currency): ExchangeRateResponse {
        val exchangeRateClient: ExchangeRateClient = ktorRpcClient.withService<ExchangeRateClient>()
        return exchangeRateClient.request(to = to, from = from)
    }
}