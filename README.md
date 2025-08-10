## ByteUI

#### ByteUI is a UI library based on JetpackCompose and planned to primarily be used for The Byte Array apps.

This library is primarily intended for internal use within The Byte Array projects. However, it is open source under the Apache 2.0 License and you are welcome to use it if you find it helpful.

See the `LICENSE` file for details.

### Installation

- In `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

- In your module `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.thebytearray:ui:Tag")
}
```

Replace `Tag` with the desired Git tag (release) from JitPack.