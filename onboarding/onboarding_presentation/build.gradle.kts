plugins {
    id(libs.plugins.library.get().pluginId)
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "${MyConfiguration.myApplicationIdConfig}.onboarding_presentation"
    compileSdk = MyConfiguration.configCompileSdkVersion

    defaultConfig {
        minSdk = MyConfiguration.configMinSdkVersion
        targetSdk = MyConfiguration.configTargetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(MyConfiguration.Modules.COREUI))
    implementation(project(MyConfiguration.MAP_MODULES.DOMAIN))
    implementation(project(MyConfiguration.MAP_MODULES.MODEL))

    //Runtime
    implementation(libs.lifecycle.runtime.ktx)

    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
//    implementation(libs.hilt.navigation)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Paging
    implementation(libs.bundles.paging)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}