// build.gradle (nivel de proyecto)
buildscript {
    repositories {
        google()  // Asegúrate de que este repositorio esté presente
        mavenCentral()
    }
    dependencies {
        // Asegúrate de que esta dependencia esté presente
        classpath ("com.google.gms:google-services:4.3.15") // Última versión disponible
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")  // Verifica la versión de Kotlin
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}