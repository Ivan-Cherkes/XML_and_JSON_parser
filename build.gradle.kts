import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
    kotlin("jvm") version "1.7.21"
    application

}
group = "me.asus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

dependencies {
    implementation("junit:junit:4.13.1")
    testImplementation(kotlin("test"))
    implementation("com.squareup.moshi:moshi:1.6.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("org.simpleframework", "simple-xml","2.7.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")


}

tasks.test {
    useJUnitPlatform()

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"

}

application {
    mainClass.set("MainKt")

}