val kotlinxRpcVersion: String by project
val ktorVersion: String by project

plugins {
    kotlin("multiplatform") version "2.1.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.9.1"
}

kotlin {
    jvm()
    sourceSets {
        commonMain.dependencies {
            api(project(":core"))
            api("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-client:$kotlinxRpcVersion")
            api("io.ktor:ktor-client-core:$ktorVersion")
        }
    }
}
