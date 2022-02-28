plugins {
    kotlin("jvm") version "1.6.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation ("io.kotest:kotest-assertions-core:5.1.0")
}

tasks.test {
    useJUnitPlatform()
}