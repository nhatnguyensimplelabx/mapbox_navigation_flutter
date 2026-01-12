plugins {
    id("com.android.library")
    id("kotlin-android")
}

group = "com.mutesasira.mapboxnavigationflutter"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2") }
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password = project.findProperty("MAPBOX_DOWNLOADS_TOKEN") as String? 
                    ?: System.getenv("MAPBOX_DOWNLOADS_TOKEN")
                    ?: throw GradleException("MAPBOX_DOWNLOADS_TOKEN isn't set. Set it to the project properties or to the environment variables.")
            }
        }
    }
}

android {
    compileSdk = 34
    namespace = "com.mutesasira.mapboxnavigationflutter"

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
    
    defaultConfig {
        minSdk = 23
        targetSdk = 34
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        disable += "InvalidPackage"
        abortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Mapbox Navigation SDK - Using stable version
    implementation("com.mapbox.navigation:copilot:2.21.0")
    implementation("com.mapbox.navigation:ui-app:2.21.0")
    implementation("com.mapbox.navigation:ui-dropin:2.21.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-location:21.1.0")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}
