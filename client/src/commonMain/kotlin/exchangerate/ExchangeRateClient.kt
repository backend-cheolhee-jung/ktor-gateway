package com.example.exchangerate

import com.example.util.Header.ACCEPT
import com.example.util.Header.APPLICATION_JSON
import com.example.util.call
import exchangerate.Currency
import exchangerate.ExchangeRateClient
import exchangerate.ExchangeRateResponse
import io.ktor.client.*
import io.ktor.http.*

class ExchangeRateClientImpl : ExchangeRateClient {
    override suspend fun request(to: Currency, from: Currency): ExchangeRateResponse {
        val url = URL.replace("{to}", to).replace("{from}", from)

        val response = httpClient.call<String>(
            url = url,
            httpMethod = HttpMethod.Get,
            headers = mapOf(ACCEPT to APPLICATION_JSON)
        )

        val rate = response
            .substringAfter(
                """
                "result":
                """.trimIndent()
            )
            .substringBefore("}")
            .trim()
            .toBigDecimalOrNull()
            ?: 0.toBigDecimal()

        return ExchangeRateResponse.of(
            from = from,
            to = to,
            rate = rate,
        )
    }

    private companion object {
        const val URL = "https://api.exchangerate.host/convert?from={from}&to={to}"
        val httpClient = HttpClient()
    }
}

private fun String.replace(oldValue: String, newValue: Currency): String {
    return this.replace(oldValue, newValue.name)
}