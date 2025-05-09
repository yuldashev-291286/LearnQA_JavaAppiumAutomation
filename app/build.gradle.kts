plugins {
    alias(libs.plugins.android.application)

    // Allure
    // https://plugins.gradle.org/plugin/io.qameta.allure

    //noinspection GradleDependency
    id("io.qameta.allure") version "2.12.0"

}


android {
    namespace = "com.example.javaappiumautomation"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.javaappiumautomation"
        minSdk = 35
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        testInstrumentationRunner = "io.qameta.allure.espresso.AllureAndroidRunner"
    }

}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.ext.junit)
    implementation(libs.monitor)
    implementation(files("src\\main\\java\\com\\example\\javaappiumautomation\\libs\\selenium-server-standalone-3.4.0.jar"))
    implementation(files("src\\main\\java\\com\\example\\javaappiumautomation\\libs\\junit-4.12.jar"))
    implementation(files("src\\main\\java\\com\\example\\javaappiumautomation\\libs\\java-client-4.1.2.jar"))
    implementation(libs.play.services.fido)
    implementation(libs.support.annotations)
    implementation(libs.annotation)
    implementation(libs.ui.android)
    implementation(libs.support.v4)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.monitor)

    androidTestImplementation (libs.allure.android.commons)
    androidTestImplementation (libs.allure.android.model)
    androidTestImplementation (libs.allure.espresso)

    androidTestImplementation (libs.kotlin.stdlib)
    androidTestImplementation (libs.junit.v412)
    androidTestImplementation (libs.uiautomator)

    implementation(libs.io.qameta.allure.gradle.plugin)

    implementation(libs.allure.kotlin.commons.test)

    // Other unrelated to Allure dependencies
    // ...
    // AspectJ Weaver
    runtimeOnly(libs.aspectjweaver)
    // JUnit
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    // Allure
    testImplementation(libs.allure.junit5)
    testImplementation(libs.allure.kotlin.commons)

}




