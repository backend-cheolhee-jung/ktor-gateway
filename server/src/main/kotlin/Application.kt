package com.example

import com.example.plugin.configureExceptionHandling
import com.example.plugin.configureFrameworks
import com.example.plugin.configureHTTP
import com.example.plugin.configureMonitoring
import com.example.plugin.configureRouting
import com.example.plugin.configureSerialization
import io.ktor.server.application.*
import kotlinx.rpc.krpc.ktor.client.KtorRpcClient
import org.koin.ktor.ext.inject

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureFrameworks()
    configureExceptionHandling()
    configureRouting()

    monitor.subscribe(ApplicationStarted) {
        val ktorRpcClient by inject<KtorRpcClient>()
        ktorRpcClient.close()
    }
}
