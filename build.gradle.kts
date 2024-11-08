plugins {
    kotlin("jvm") version "2.0.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("junit:junit:4.13.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("io.ktor:ktor-server-core:3.0.0")
    implementation("io.ktor:ktor-server-netty:3.0.0")
    implementation("io.ktor:ktor-server-content-negotiation:2.0.0") // JSON handling
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0") // JSON serialization support
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}