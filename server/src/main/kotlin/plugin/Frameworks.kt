package com.example.plugin

import com.example.plugin.module.exchangeRateModule
import com.example.plugin.module.ktorRpcClientModule
import com.example.plugin.module.newsModule
import com.example.plugin.module.weatherModule
import io.ktor.server.application.*
import kotlinx.rpc.krpc.ktor.server.Krpc
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(ktorRpcClientModule, weatherModule, newsModule, exchangeRateModule)
    }
    install(Krpc)
}