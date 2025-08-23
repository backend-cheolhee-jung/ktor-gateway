package exchangerate

import kotlinx.rpc.annotations.Rpc

@Rpc
interface ExchangeRateClient {
    suspend fun request(to: Currency, from: Currency): ExchangeRateResponse
}