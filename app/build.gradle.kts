import java.util.Properties

plugins {
    alias(libs.plugins.podium.android.application)
    alias(libs.plugins.podium.android.compose)
}

// Signing keys and passwords stay outside git in ignored keystore.properties
// or GitHub Actions environment variables.
val signing = Properties().apply {
    rootProject.file("keystore.properties").takeIf { it.isFile }?.inputStream()?.use { load(it) }
}
fun secret(key: String, env: String): String? = signing.getProperty(key) ?: System.getenv(env)

val debugStore = secret("debugStoreFile", "PODIUM_DEBUG_KEYSTORE_FILE")
val releaseStore = secret("storeFile", "PODIUM_KEYSTORE_FILE")

android {
    namespace = "app.podium"
    defaultConfig {
        applicationId = "app.podmusic"
        versionCode = 1
        versionName = "0.1.0"
    }
    buildFeatures {
        buildConfig = true
    }
    lint {
        disable += "InvalidFragmentVersionForActivityResult"
    }
    androidResources {
        noCompress += "tflite"
    }
    signingConfigs {
        // Keep AGP's normal debug signing for local builds when no persistent key is configured.
        if (debugStore != null) {
            getByName("debug") {
                storeFile = file(debugStore)
                storePassword = secret("debugStorePassword", "PODIUM_DEBUG_KEYSTORE_PASSWORD")
                keyAlias = secret("debugKeyAlias", "PODIUM_DEBUG_KEY_ALIAS")
                keyPassword = secret("debugKeyPassword", "PODIUM_DEBUG_KEY_PASSWORD")
            }
        }
        if (releaseStore != null) {
            create("release") {
                storeFile = file(releaseStore)
                storePassword = secret("storePassword", "PODIUM_KEYSTORE_PASSWORD")
                keyAlias = secret("keyAlias", "PODIUM_KEY_ALIAS")
                keyPassword = secret("keyPassword", "PODIUM_KEY_PASSWORD")
            }
        }
    }
    val sideBySide = providers.gradleProperty("podium.applicationIdSuffix").orNull
    buildTypes {
        debug {
            applicationIdSuffix = sideBySide ?: ".debug"
            if (debugStore != null) {
                signingConfig = signingConfigs.getByName("debug")
            }
            isDebuggable = providers.gradleProperty("podium.debuggable").orNull == "true"
            if (providers.gradleProperty("podium.minifyDebug").orNull == "true") {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        release {
            sideBySide?.let { applicationIdSuffix = it }
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.findByName("release")
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:interaction"))
    implementation(project(":core:database"))
    implementation(project(":core:lyrics"))
    implementation(project(":sources:api"))
    implementation(project(":sources:local"))
    implementation(project(":player:api"))
    implementation(project(":player:service"))
    implementation(project(":player:remote"))
    implementation(project(":feature:library"))
    implementation(project(":feature:online"))
    implementation(project(":sources:youtubemusic"))
    implementation(project(":sources:addon"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.javascriptengine)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(project(":feature:nowplaying"))
    implementation(project(":feature:settings"))
    debugImplementation(project(":sources:test"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.litert)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.room3.runtime)
    testImplementation(libs.sqlite.bundled.jvm)
    testImplementation(testFixtures(project(":sources:api")))

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(project(":sources:test"))
}

configurations.matching { it.name.endsWith("UnitTestRuntimeClasspath") }.configureEach {
    exclude(group = "androidx.sqlite", module = "sqlite-bundled-android")
}
