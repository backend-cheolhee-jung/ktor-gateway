package com.example.router

import io.ktor.http.ContentType
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.healthCheck() {
    get("/health-check") {
        call.respondText("OK", contentType = ContentType.Text.Plain)
    }
}