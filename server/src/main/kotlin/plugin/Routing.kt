package com.example.plugin

import com.example.router.feed
import com.example.router.healthCheck
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        healthCheck()
        feed()
    }
}
