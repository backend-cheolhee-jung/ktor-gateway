val koin_version: String by project
val kotlin_version: String by project
val kotlinx_rpc_version: String by project
val ktor_version: String by project
val logback_version: String by project

plugins {
    kotlin("multiplatform") version "2.1.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.9.1"
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-rpc-krpc-serialization-json:$kotlinx_rpc_version")
            api("org.jetbrains.kotlinx:kotlinx-rpc-core:$kotlinx_rpc_version")
        }
    }
}
