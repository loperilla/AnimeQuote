// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    dependencies {
        classpath(libs.kotlin.gradle)
        classpath(libs.build.gradle)
        classpath(libs.hilt.gradle)
        classpath(libs.kotlin.serialization)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `version-catalog`
    `java-gradle-plugin`
    alias(libs.plugins.application) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ben.manes.versions)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.ktor.serialization)
}

// Variable para pillar versiones estables
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

// Tarea para setear estas versiones estables al actualizar dependencias
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}