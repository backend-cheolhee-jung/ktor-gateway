package com.example.plugin.module

import Environment
import io.ktor.client.*
import io.ktor.http.*
import kotlinx.rpc.krpc.ktor.client.KtorRpcClient
import kotlinx.rpc.krpc.ktor.client.installKrpc
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import org.koin.dsl.module

val ktorRpcClientModule = module {
    single<KtorRpcClient> {
        HttpClient {
            installKrpc {
                waitForServices = true
            }
        }.rpc {
            url {
                host = Environment.HOST
                port = Environment.PORT
                encodedPath = Environment.ENCODED_PATH
            }

            rpcConfig {
                serialization {
                    json()
                }
            }
        }
    }
}