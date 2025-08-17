val kotlinxRpcVersion: String by project
val ktorVersion: String by project

plugins {
    kotlin("multiplatform") version "2.1.10"
    id("io.ktor.plugin") version "3.2.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.9.1"
}

application {
    mainClass = "io.ktor.server.cio.EngineMain"
}

kotlin {
    jvm()
    sourceSets {
        commonMain.dependencies {
            api(project(":core"))
            api("com.fasterxml.jackson.module:jackson-module-kotlin")
            api("io.ktor:ktor-client-core:$ktorVersion")
            api("io.ktor:ktor-client-cio-jvm")
            implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-server:${kotlinxRpcVersion}")
            implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-client:$kotlinxRpcVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-client:$kotlinxRpcVersion")
            implementation("io.ktor:ktor-server-core")
            implementation("io.ktor:ktor-server-cio")
            implementation("com.fasterxml.jackson.core:jackson-annotations:3.0-rc5")
        }
    }
}
