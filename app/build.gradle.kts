plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.kotlin.examenmovil"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlin.examenmovil"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    //Data binding
    implementation ("androidx.databinding:databinding-runtime:7.1.2")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation ("jp.wasabeef:glide-transformations:4.3.0")
    //Activity
    implementation ("androidx.activity:activity-ktx:1.5.0")
    //Material
    implementation ("com.google.android.material:material:1.9.0")
    //Fragment
    implementation ("androidx.fragment:fragment-ktx:1.5.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    // OkHttp
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

}