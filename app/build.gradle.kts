import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.dev.ksp)
}

val debugProps =
    Properties().apply {
        load(
            rootProject.file("debug.env.properties")
                .reader()
        )
    }

val prodProps = Properties().apply {
    load(
        rootProject.file("prod.env.properties")
            .reader()
    )
}

android {
    namespace =
        "com.assignment.pokemoncatcher"
    compileSdk = 34

    defaultConfig {
        applicationId =
            "com.assignment.pokemoncatcher"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "POKE_API_BASE_URL",
                "\"${
                    debugProps.getProperty(
                        "pokemonApiBaseUrl"
                    )
                }\""
            )

            buildConfigField(
                "String",
                "POKE_UTIL_BASE_URL",
                "\"${
                    debugProps.getProperty(
                        "pokeUtilBaseUrl"
                    )
                }\""
            )
        }
        release {
            isMinifyEnabled = false
            buildConfigField(
                "String",
                "POKE_API_BASE_URL",
                "\"${
                    prodProps.getProperty(
                        "pokemonApiBaseUrl"
                    )
                }\""
            )

            buildConfigField(
                "String",
                "POKE_UTIL_BASE_URL",
                "\"${
                    prodProps.getProperty(
                        "pokeUtilBaseUrl"
                    )
                }\""
            )

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_17
        targetCompatibility =
            JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.versions.kotlinCompilerExtension.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    //lifecycle
    implementation(libs.bundles.lifecycle)

    //compose ui
    val composeBom =
        platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.bundles.compose)

    //dagger hilt
    implementation(libs.bundles.hilt)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)

    //json serialization
    implementation(libs.kotlinx.serialization.json)

    //Coroutines
    implementation(libs.bundles.coroutine)

    //Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    //Ktor Http Client
    implementation(libs.bundles.ktor.http.client)

    //testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}