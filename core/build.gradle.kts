val kotlinxRpcVersion: String by project
val arrowVersion: String by project
val kotlinxCoroutinesVersion: String by project

plugins {
    kotlin("multiplatform") version "2.1.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.9.1"
}

kotlin {
    jvm()
    sourceSets {
        commonMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-rpc-krpc-serialization-json:$kotlinxRpcVersion")
            api("org.jetbrains.kotlinx:kotlinx-rpc-core:$kotlinxRpcVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            implementation("io.arrow-kt:arrow-core:$arrowVersion")
            implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")
        }
    }
}
