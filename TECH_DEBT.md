# Tech Debt Cleanup - 2025-11-16

## Summary

This document tracks the technical debt cleanup performed on the Jesture project, bringing it from 2016-era tooling to modern 2025 standards.

## Changes Made

### 1. Gradle Update
- **Before**: Gradle 6.1-rc-1 (release candidate from 2020)
- **After**: Gradle 8.5
- **Files**: `gradle/wrapper/gradle-wrapper.properties`

### 2. Kotlin Update
- **Before**: Kotlin 1.3.61 (2019)
- **After**: Kotlin 1.9.22
- **Files**: All `build.gradle` files
- **Impact**: Modern Kotlin features, better performance, security updates

### 3. JCenter/Bintray Removal
- **Removed**: All references to JCenter (shut down in 2021)
- **Removed**: All references to Bintray repositories (shut down in 2021)
- **Removed**: Bintray Gradle plugin and configuration
- **Files**: All `build.gradle` files
- **Impact**: Project now uses only Maven Central and Google repositories

### 4. Android Gradle Plugin Update
- **Before**: 4.0.0-alpha08 (alpha version from 2020)
- **After**: 8.2.0
- **Files**: `clients/android/app/build.gradle`

### 5. Android SDK Updates
- **Before**:
  - compileSdkVersion: 28 (Android 9, 2018)
  - targetSdkVersion: 28
  - minSdkVersion: 17 (Android 4.2)
- **After**:
  - compileSdk: 34 (Android 14)
  - targetSdkVersion: 34
  - minSdkVersion: 21 (Android 5.0)
- **Files**: `clients/android/app/build.gradle`
- **Impact**: Dropped support for Android 4.2-4.4, now requires Android 5.0+

### 6. Gradle Configuration Modernization
- **Removed**: Deprecated `compile` → replaced with `implementation`
- **Removed**: Deprecated `testCompile` → replaced with `testImplementation`
- **Removed**: Deprecated `maven` plugin from recognizers module
- **Added**: Modern compile options (Java 17 compatibility)
- **Added**: Kotlin JVM target configuration (JVM 17)
- **Added**: `namespace` declaration for Android
- **Files**: All `build.gradle` files

### 7. Dependency Updates

#### Core Library (`recognizers`)
- **Before**: JUnit 4.12
- **After**: JUnit 4.13.2
- **Impact**: Security fixes and improvements

#### Android App
- **Removed**: Anko 0.8.2 (deprecated/abandoned library)
- **Added**: AndroidX Core KTX 1.12.0
- **Added**: AndroidX AppCompat 1.6.1
- **Updated**: Material Design 1.2.0-alpha03 → 1.11.0 (stable)
- **Files**: `clients/android/app/build.gradle`, `HelloRecognizerActivity.kt`

### 8. Android Code Refactoring
- **Removed**: All Anko DSL usage from Android activity
- **Replaced with**: Standard Android View creation (LinearLayout, programmatic views)
- **Files**: `clients/android/app/src/main/java/com/jesterlabs/recognizer/ui/HelloRecognizerActivity.kt`
- **Impact**: Cleaner, maintainable code using standard Android APIs

### 9. CI/CD Updates

#### Travis CI
- **Updated**: Build tools 29.0.2 → 34.0.0
- **Updated**: Android SDK 28 → 34
- **Added**: JDK 17 configuration
- **Added**: Gradle caching
- **Added**: Explicit build and test commands
- **Files**: `.travis.yml`

#### GitHub Actions (NEW)
- **Added**: Modern CI/CD workflow using GitHub Actions
- **Features**:
  - Builds on push and PR to main/master/develop
  - Uses JDK 17 with Temurin distribution
  - Gradle caching for faster builds
  - Test result and artifact uploads
- **Files**: `.github/workflows/build.yml`

### 10. Gradle Configuration
- **Added**: `gradle.properties` with modern settings
- **Features**:
  - AndroidX migration flags
  - Gradle performance optimizations
  - JVM memory settings
  - Kotlin code style configuration

## Breaking Changes

### For Users
1. **Android**: Now requires Android 5.0+ (API 21) instead of Android 4.2+ (API 17)
2. **Build**: Requires JDK 17 instead of JDK 8

### For Developers
1. **Bintray Publishing**: Removed (service shut down)
   - Alternative: Publish to Maven Central or GitHub Packages
2. **JCenter**: No longer available
   - All dependencies now from Maven Central
3. **Anko**: No longer used in Android client
   - Use standard Android View APIs

## Remaining Tech Debt

### Low Priority
1. **JUnit 5**: Consider migrating from JUnit 4 to JUnit 5 (Jupiter)
2. **Kotlin Multiplatform**: Could modernize to use Kotlin Multiplatform instead of separate modules
3. **Android Compose**: Could migrate Android UI to Jetpack Compose
4. **Version Catalogs**: Could use Gradle version catalogs for dependency management
5. **Build Script Migration**: Could migrate from Groovy to Kotlin DSL for build.gradle files

### Documentation
1. **README**: Should update to reflect new minimum requirements
2. **CLAUDE.md**: Should update with new versions and removed dependencies

## Migration Guide

### For New Developers
1. Ensure JDK 17 is installed
2. Clone repository
3. Run `./gradlew build` to build all modules
4. Run `./gradlew test` to run tests

### For Existing Developers
1. Update local JDK to version 17
2. Delete `.gradle/` directory and rebuild: `./gradlew clean build`
3. For Android development, ensure Android SDK 34 is installed
4. Remove any cached Gradle or Maven artifacts from old JCenter

## Testing Status
- Unit tests should pass with updated JUnit version
- Android app requires manual testing on device/emulator
- Java/Swing client should work with JDK 17

## References
- [Gradle 8.5 Release Notes](https://docs.gradle.org/8.5/release-notes.html)
- [Kotlin 1.9.22 Changelog](https://github.com/JetBrains/kotlin/releases/tag/v1.9.22)
- [Android Gradle Plugin 8.2.0](https://developer.android.com/build/releases/gradle-plugin)
- [JCenter Shutdown Announcement](https://jfrog.com/blog/into-the-sunset-bintray-jcenter-gocenter-and-chartcenter/)

---
*Generated during tech debt cleanup session on 2025-11-16*
