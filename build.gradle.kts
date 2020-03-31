import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
    kotlin("plugin.jpa") version "1.3.71"
}

group = "eu.a-square"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

repositories {
    mavenCentral()
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") {
        because("It's the main dependency for the project")
    }
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
        because("The data layer is being accessed with JPA")
    }
    implementation("org.springframework.boot:spring-boot-starter-mustache") {
        because("The API documentation uses mustache templates")
    }
    implementation("org.springframework.boot:spring-boot-starter-security") {
        because("We want users to authenticate before sharing")
    }
    implementation("org.jetbrains.kotlin:kotlin-reflect") {
        because("We need class reflection ('::class') for Spring tests and several other things")
    }
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") {
        because("Java")
    }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core") {
        because("We want to use coroutines")
    }
    implementation("org.jetbrains.kotlin:kotlin-allopen") {
        because("We need open classes for Spring annotations")
    }
    implementation("org.jetbrains.kotlin:kotlin-noarg") {
        because("This is necessary for JPA")
    }
    implementation("org.mariadb.jdbc:mariadb-java-client") {
        because("We use MariaDB for persistence")
    }
    implementation("org.flywaydb:flyway-core") {
        because("We migrate our database with flyway scripts")
    }
    implementation("org.jsoup:jsoup:1.13.1") {
        because("It can read and parse target websites")
    }

    developmentOnly("org.springframework.boot:spring-boot-devtools") {
        because("It enables hot reloading and other cool stuff")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit", "junit")
        exclude("org.mockito", "mockito-core")
        because("Testing tools for Spring Boot")
    }
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2") {
        because("This is a newer version than the one provided by Spring Boot Starter Test")
    }
    testImplementation("io.mockk:mockk:1.9.3") {
        because("This allows for better kotlin-style mocking")
    }
    testImplementation("org.springframework.security:spring-security-test") {
        because("We need to test the security layer")
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}