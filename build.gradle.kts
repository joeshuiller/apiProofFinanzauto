plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.6"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "2.2.21"
}

group = "com.finanzauto.asisya.janes"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.security:spring-security-webauthn")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
    // JJWT dependencies
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    // Springdoc OpenAPI for Swagger UI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")
    implementation("org.springframework.boot:spring-boot-starter-mail")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity") // 1. Anotación de entidad (Obligatoria para runtime)
	annotation("jakarta.persistence.MappedSuperclass") // 2. Anotación de superclase mapeada
	annotation("jakarta.persistence.Embeddable") // 3. Anotación de embebible
	annotation("com.fasterxml.jackson.annotation.JsonIgnoreProperties") // 4. Anotación de ignorar propiedades (Obligatoria para runtime)
	annotation("jakarta.persistence.Converter") // 5. Anotación de convertidor (Obligatoria para runtime)
	annotation("jakarta.persistence.Table")
	annotation("jakarta.persistence.Transient")
	annotation("jakarta.persistence.Column")
	annotation("jakarta.persistence.JoinColumn")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
