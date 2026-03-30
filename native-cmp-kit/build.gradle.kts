import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "com.nativecmp.kit"
version = "1.0.0"

kotlin {
    jvm()
    androidLibrary {
        namespace = "com.nativecmp.kit"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_11
                )
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "native-cmp-kit", version.toString())

    pom {
        name = "Native-CMP-UI-Kit"
        description = "NativeCMP is a Compose Multiplatform component library that provides a unified Compose API for platform-native Android and iOS UI components."
        inceptionYear = "2024"
        url = "https://github.com/waqas028/Native-CMP-UI-Kit"
        licenses {
            license {
                name = "Apache-2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "waqas028"
                name = "Muhammad Waqas"
                email = "waqaswaseem679@gmail.com"
                url = "https://github.com/waqas028"
            }
        }
        scm {
            url = "https://github.com/waqas028/Native-CMP-UI-Kit"
            connection = "scm:git:git://github.com/waqas028/Native-CMP-UI-Kit.git"
            developerConnection = "scm:git:ssh://github.com/waqas028/Native-CMP-UI-Kit.git"
        }
    }
}
