import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "br.org.acal"
version = "0.0.1-SNAPSHOT"
val cumcumber_version = "7.15.0"
val wiremock_version = "3.4.0"
val test_container_version = "3.2.3"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {

	//Spring
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("jakarta.validation:jakarta.validation-api")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")


	//log
	implementation("ch.qos.logback:logback-classic")
	implementation("org.slf4j:slf4j-api")

	//ULID
	implementation("io.azam.ulidj:ulidj:1.0.4")

	//Serialization
	implementation("com.google.code.gson:gson")

	//database
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.2.3")
	implementation("org.springframework.data:spring-data-mongodb:4.2.3")


	//metrics
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//test
	//test startup
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.rest-assured:kotlin-extensions")
	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude(module = "junit")
		exclude(module = "mockito-core")
	}
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("org.mockito:mockito-core")

	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("io.mockk:mockk:1.12.2")
	testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")

	//teste container
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:testcontainers")
	testImplementation("org.testcontainers:mongodb")

	//teste cubumber
	testImplementation("io.cucumber:cucumber-java:$cumcumber_version")
	testImplementation("io.cucumber:cucumber-java8:$cumcumber_version")
	testImplementation("io.cucumber:cucumber-junit:$cumcumber_version")
	testImplementation("io.cucumber:cucumber-spring:$cumcumber_version")
	
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
