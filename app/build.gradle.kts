plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val apiKey = project.findProperty("API_KEY") ?: "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ"

android {
    namespace = "com.krishnajeena.moviemash"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.krishnajeena.moviemash"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${apiKey}\"")
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"${apiKey}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

}


dependencies {

    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI and Material
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.material3.android)

    // Unit and Instrumentation Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Networking - Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0") // Retrofit + RxJava adapter



    // Dependency Injection - Koin
    implementation("io.insert-koin:koin-androidx-compose:4.1.0-Beta5")
    implementation("io.insert-koin:koin-androidx-compose-navigation:4.1.0-Beta5")
    implementation("io.insert-koin:koin-android:4.1.0-Beta5")
    implementation("io.insert-koin:koin-compose:4.1.0-Beta5")
    implementation("io.insert-koin:koin-compose-viewmodel:4.1.0-Beta5")
//    implementation("io.insert-koin:koin-compose-viewmodel-navigation:$koin_version")

    // RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.1.7")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")

    // Navigation and Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7") // Or use BOM
    implementation("androidx.navigation:navigation-compose:2.8.5")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("io.coil-kt.coil3:coil-compose:3.0.4")

    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.1")

    implementation("androidx.compose.material:material:1.7.6")

    implementation("androidx.compose.material:material-icons-core:1.5.3")

    
}
