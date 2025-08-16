package weather

import kotlinx.rpc.annotations.Rpc

@Rpc
interface WeatherClient {
    suspend fun request(city: City): WeatherResponse
}