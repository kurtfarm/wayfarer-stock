plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":wayfarer-stock-app"))

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("com.dkprint.api.WayfarerStockApplicationKt")
}
