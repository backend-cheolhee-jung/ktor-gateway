package com.example.plugin

import io.ktor.server.application.*
import kotlinx.rpc.krpc.ktor.server.Krpc
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(
            module {}
        )
    }
    install(Krpc)
}
