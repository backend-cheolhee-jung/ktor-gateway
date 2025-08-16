package com.example

import com.example.plugin.configureFrameworks
import com.example.plugin.configureHTTP
import com.example.plugin.configureMonitoring
import com.example.plugin.configureRouting
import com.example.plugin.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureFrameworks()
    configureRouting()
}
