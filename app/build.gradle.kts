plugins {
    alias(libs.plugins.android.application)

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
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.monitor)

    androidTestImplementation (libs.allure.android.commons)
    androidTestImplementation (libs.allure.android.model)
    androidTestImplementation (libs.allure.espresso)

    androidTestImplementation (libs.kotlin.stdlib)
    androidTestImplementation (libs.junit.v412)
    androidTestImplementation (libs.uiautomator)

}

repositories {
//    mavenCentral()
//    maven {
//        url; //"https://dl.bintray.com/qameta/maven"
//    }

}



