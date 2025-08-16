import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json
import weather.WeatherClient
import weather.WeatherClientImpl

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    install(Krpc)

    routing {
        rpc("/weather") {
            url {
                host = "localhost"
                port = 8080
                encodedPath = "cherhy.jung"
            }

            rpcConfig {
                serialization {
                    json()
                }
            }

            registerService<WeatherClient> { WeatherClientImpl() }
        }
    }
}