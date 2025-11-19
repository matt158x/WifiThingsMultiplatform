import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val iosArm64Target = iosArm64()
    val iosSimulatorArm64Target = iosSimulatorArm64()

    configure(listOf(iosArm64Target, iosSimulatorArm64Target)) {
        binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // --- Ktor (core, content negotiation, JSON) ---
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)

                // --- SQLDelight ---
                implementation(libs.runtime)

                // --- Kotlinx ---
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)

                // --- Dependency Injection ---
                implementation(libs.koin.core)
                implementation(libs.koin.compose.mp)
                implementation(libs.koin.compose.viewmodel)

                // --- Compose Multiplatform ---
                implementation(compose.materialIconsExtended)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
            }
        }

        val androidMain by getting {
            dependencies {
                // --- Ktor Android Engine ---
                implementation(libs.ktor.client.android)

                // --- SQLDelight Android Driver ---
                implementation(libs.android.driver)

                // --- Android & Compose integration ---
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.androidx.compose)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            iosArm64Target.compilations["main"].defaultSourceSet.dependsOn(this)
            iosSimulatorArm64Target.compilations["main"].defaultSourceSet.dependsOn(this)

            dependencies {
                // --- Ktor Darwin Engine ---
                implementation(libs.ktor.client.darwin)

                // --- SQLDelight iOS (Native) Driver ---
                implementation(libs.native.driver)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.wifithing.db")
        }
    }
}

android {
    namespace = "com.example.wifithing"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.wifithing"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
