val koin_version: String by project
val kotlin_version: String by project
val kotlinx_rpc_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.2.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.9.1"
}

application {
    mainClass = "io.ktor.server.cio.EngineMain"
}

dependencies {
    implementation(project(":core"))
    implementation("io.ktor:ktor-server-caching-headers")
    implementation("io.ktor:ktor-server-default-headers")
    implementation("io.ktor:ktor-server-forwarded-header")
    implementation("io.ktor:ktor-server-http-redirect")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-host-common")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-call-logging")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-serialization-jackson")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-server:$kotlinx_rpc_version")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-client:$kotlinx_rpc_version")
    implementation("io.ktor:ktor-server-cio")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
