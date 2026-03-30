# Native-CMP-UI-Kit

**NativeCMP** is a Compose Multiplatform component library that provides a unified Compose API for platform-native Android and iOS UI components.

## Features
- **Unified API**: Write once in Compose, get platform-native UI on Android and iOS.
- **Platform Native**: 
  - **Android**: Uses Material3 components.
  - **iOS**: Uses modern `UIKit` components (`UIButtonConfiguration`, etc.) via `UIKitView`.
- **Customizable**: Comprehensive parameters for colors, shapes, borders, and more.
- **Lightweight**: Minimal overhead, utilizing the best interop practices.

## Installation

### 1. Add JitPack Repository
Add this to your `settings.gradle.kts` (or root `build.gradle.kts`):
```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

### 2. Add Dependency
Add this to your common source set in `build.gradle.kts`:
```kotlin
commonMain.dependencies {
    implementation("io.github.waqas028:native-cmp-kit:1.0.0")
}
```
*Note: If `1.0.0` doesn't resolve, try using the commit hash or a specific tag.*

## Usage
```kotlin
import io.github.waqas028.nativecmp.NativeButton

@Composable
fun MyScreen() {
    NativeButton(
        text = "Click Me",
        onClick = { /* Handle click */ }
    )
}
```

## Supported Components (Phase 1)
- **NativeButton**: Filled, Outlined, Text, Tonal, and Icon variants.

## Developer Info
- **Name**: Muhammad Waqas
- **GitHub**: [@waqas028](https://github.com/waqas028)
- **Email**: [waqaswaseem679@gmail.com](mailto:waqaswaseem679@gmail.com)

## License
Apache License 2.0
