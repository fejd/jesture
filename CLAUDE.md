# CLAUDE.md - AI Assistant Guide for Jesture

## Project Overview

**Jesture** is a gesture recognition library written in Kotlin that implements the $1 Unistroke Recognizer algorithm, developed by Jacob O. Wobbrock, Andrew D. Wilson, and Yang Li. The project provides a cross-platform gesture recognition solution targeting Java, Android, and (planned) JavaScript environments.

- **Author**: Fredrik Henricsson (Copyright 2016)
- **License**: MIT
- **Current Version**: 0.1.0
- **Status**: Under active development
- **Repository**: Multi-module Gradle project

## Codebase Structure

### Module Architecture

The project follows a clean, modular architecture with three main components:

```
jesture/
├── recognizers/              # Core library (published artifact)
├── clients/
│   ├── android/app/         # Android demo application
│   └── java/                # Java/Swing demo application
├── docs/                    # Documentation (dollar.pdf)
├── lib/                     # Additional libraries
└── gradle/                  # Gradle wrapper
```

### Core Modules

#### 1. recognizers/ - Core Library Module

**Location**: `/home/user/jesture/recognizers`

**Purpose**: Contains the gesture recognition algorithm implementation and utilities.

**Key Files**:
- `src/main/kotlin/com/jesterlabs/jesture/recognizers/`
  - `onedollar/OneDollarRecognizer.kt` - Main recognizer class (59 lines)
  - `onedollar/Template.kt` - Gesture template representation (38 lines)
  - `onedollar/Result.kt` - Recognition result data class (22 lines)
  - `onedollar/util/DollarUtils.kt` - Core algorithm utilities (184 lines)
  - `common/data/Point.kt` - 2D point data class
  - `common/data/Rectangle.kt` - Bounding box representation
  - `common/util/StrokeFactory.kt` - Provides 16 default gesture templates (69 lines)

**Build Configuration**:
- Kotlin version: 1.3.61
- Published as: `com.jesterlabs.jesture:recognizers:0.1.0`
- Publishing: Bintray (JCenter) and local Maven
- Dependencies: kotlin-stdlib, JUnit 4.12 (test)

**Available Gestures** (from StrokeFactory):
```
TRIANGLE, X, RECTANGLE, CIRCLE, CHECK, CARET, ZIGZAG, ARROW,
LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET, V, DELETE,
LEFT_CURLY_BRACE, RIGHT_CURLY_BRACE, STAR, PIGTAIL
```

#### 2. clients/android/app/ - Android Demo

**Location**: `/home/user/jesture/clients/android/app`

**Purpose**: Android application demonstrating gesture recognition with touch input.

**Key Files**:
- `src/main/kotlin/com/jesterlabs/recognizer/HelloRecognizerActivity.kt` (136 lines)
  - Custom `ResultView` that captures touch events
  - Real-time drawing of gestures
  - Displays recognition results on screen

**Build Configuration**:
- compileSdkVersion: 28
- minSdkVersion: 17
- targetSdkVersion: 28
- Package: `com.jesterlabs.recognizer`
- Dependencies:
  - `:recognizers` module
  - Anko DSL 0.8.2
  - Material Design 1.2.0-alpha03

#### 3. clients/java/ - Java/Swing Demo

**Location**: `/home/user/jesture/clients/java`

**Purpose**: Desktop Java application with Swing UI for gesture recognition.

**Key Files**:
- `src/main/kotlin/com/jesterlabs/recognizer/java/JavaClient.kt` (100 lines)
  - `StrokeCanvas`: Custom Canvas for drawing
  - `MouseRecognizer`: Captures mouse events and recognizes gestures

**Build Configuration**:
- Main class: `com.jesterlabs.recognizer.java.JavaClientKt`
- Application plugin enabled
- Default task: `run`

## Development Workflows

### Building the Project

```bash
# Build all modules
./gradlew build

# Build specific module
./gradlew :recognizers:build
./gradlew :clients:android:app:build
./gradlew :clients:java:build

# Run Java client
./gradlew :clients:java:run
```

### Testing

**Test Location**: `/home/user/jesture/recognizers/src/test/kotlin/com/jesterlabs/jesture/recognizers/onedollar/OneDollarTest.kt`

**Running Tests**:
```bash
./gradlew test
./gradlew :recognizers:test --info  # Verbose output
```

**Test Coverage**:
- `testRecognizeCheck()`: Validates successful CHECK gesture recognition (score > 0.94)
- `testRecognizeFail()`: Validates failure with insufficient points

**Testing Conventions**:
- Tests always run (not cached)
- Verbose logging enabled for debugging
- Uses JUnit 4

### Publishing

The library uses Bintray for publishing artifacts. The `recognizers` module includes:
- Maven publishing configuration
- Bintray deployment setup
- Local Maven repository support for development

**Development Publishing**:
```bash
./gradlew :recognizers:publishToMavenLocal
```

### CI/CD

**Travis CI** is configured for automated builds:
- Configuration: `.travis.yml`
- Platform: Android
- Runs on code commits

## Algorithm Implementation

### $1 Unistroke Recognizer

The core algorithm processes gestures through 5 steps:

1. **Resample** (`resample()`): Normalize to 64 evenly-spaced points
2. **Rotate** (`rotateBy()`): Align to indicative angle
3. **Scale** (`scaleTo()`): Normalize to 250x250 square
4. **Translate** (`translateTo()`): Move centroid to origin
5. **Match** (`distanceAtBestAngle()`): Compare against templates using golden section search

**Key Constants** (in `DollarUtils.kt`):
```kotlin
NUMPOINTS = 64              // Standard resampling size
SQUARE_SIZE = 250.0         // Normalization square dimension
ANGLE_RANGE = 45.0          // Search range in degrees
ANGLE_PRECISION = 2.0       // Angular precision
PHI = 0.5 * (-1.0 + sqrt(5.0))  // Golden ratio
```

**Recognition Score**: Returns a value between 0.0 and 1.0, where higher values indicate better matches.

### Usage Example

```kotlin
// Initialize recognizer with default templates
val recognizer = OneDollarRecognizer()

// Or with custom templates
val customRecognizer = OneDollarRecognizer(listOf(
    Template("MyGesture", listOf(/* points */))
))

// Recognize a gesture
val points = listOf<Point>(/* user input points */)
val result = recognizer.recognize(points)

println("Recognized: ${result.name} with score ${result.score}")
```

## Coding Conventions

### Language and Style

- **Primary Language**: Kotlin
- **Kotlin Version**: 1.3.61
- **Package Naming**: `com.jesterlabs.jesture.*`
- **File Organization**: One class per file (Kotlin convention)

### Code Patterns

1. **Data Classes**: Used for simple data structures (Point, Rectangle, Result, Template)
2. **Immutability**: Prefer `val` over `var`, pure functions
3. **Extension Functions**: Not heavily used, but available
4. **Named Parameters**: Use for clarity in function calls
5. **Type Inference**: Leveraged throughout

### Architecture Patterns

- **Factory Pattern**: `StrokeFactory` provides pre-defined templates
- **Algorithm as Utilities**: Core $1 algorithm in static utility functions
- **Template Method**: Recognition process follows fixed steps

## Key Technical Details

### Coordinate System

- Origin: Top-left (0, 0)
- X-axis: Increases to the right
- Y-axis: Increases downward (standard screen coordinates)

### Performance Considerations

- **Resampling**: Reduces variable-length strokes to fixed 64 points
- **Golden Section Search**: Efficient angle matching (O(log n))
- **Pre-computed Templates**: Templates are preprocessed and stored

### Minimum Requirements

- **Points Required**: Minimum 2 points for recognition (though 64+ recommended for accuracy)
- **Android**: API level 17+ (Android 4.2)
- **Java**: Compatible with standard Java/Swing

## Working with This Codebase

### When Adding New Features

1. **New Recognizers**: Add to `recognizers/` module under appropriate package
2. **New Gestures**: Add to `StrokeFactory.kt` or create custom `Template` instances
3. **Client Updates**: Modify respective client modules independently
4. **Tests**: Add to `recognizers/src/test/kotlin/` following existing patterns

### Common Tasks

**Add a New Gesture Template**:
```kotlin
// In StrokeFactory.kt
fun newGesture(): List<Point> {
    return listOf(
        Point(/* coordinates */)
        // ... more points
    )
}
```

**Integrate into Android App**:
- Touch events are captured in `ResultView`
- Points collected on ACTION_MOVE
- Recognition triggered on ACTION_UP

**Integrate into Java/Swing App**:
- Mouse events captured in `MouseRecognizer`
- Points collected on mouseDragged
- Recognition triggered on mouseReleased

### Dependencies and Build System

**Root build.gradle**:
- Defines repository configuration for all modules
- Uses Google, Maven Central, and JCenter
- Includes local Maven repository workaround

**Module build.gradle files**:
- `recognizers/build.gradle`: Library publishing configuration
- `clients/android/app/build.gradle`: Android app configuration
- `clients/java/build.gradle`: Java application configuration

### File Locations Reference

**Configuration Files**:
- `/home/user/jesture/build.gradle` - Root build configuration
- `/home/user/jesture/settings.gradle` - Module inclusion
- `/home/user/jesture/.travis.yml` - CI configuration
- `/home/user/jesture/.gitignore` - Git exclusions

**Documentation**:
- `/home/user/jesture/README.md` - Project readme
- `/home/user/jesture/LICENSE` - MIT license
- `/home/user/jesture/docs/dollar.pdf` - Original $1 algorithm paper

**Build Tools**:
- `/home/user/jesture/gradlew` - Unix Gradle wrapper
- `/home/user/jesture/gradlew.bat` - Windows Gradle wrapper
- `/home/user/jesture/gradle/` - Gradle wrapper files

## Future Development Goals

According to the README, planned features include:

1. **JavaScript Client**: Cross-platform web support
2. **Additional Recognizers**:
   - 1¢ Recognizer
   - $P Point-Cloud Recognizer
   - $N Multistroke Recognizer

### When Working on These Features

- **JavaScript**: Consider adding `clients/javascript/` module
- **New Recognizers**: Add parallel packages to `onedollar/` (e.g., `pdollar/`, `ndollar/`)
- **Common Code**: Extract shared utilities to `common/` package

## Troubleshooting

### Build Issues

- **Gradle Version**: Uses Gradle 6.1-rc-1 (check `gradle/wrapper/gradle-wrapper.properties`)
- **JCenter Deprecation**: May need to migrate from JCenter to Maven Central
- **Local Maven**: Uses custom path to local Maven repository

### Test Failures

- Tests use hard-coded score thresholds (e.g., `> 0.94`)
- Ensure sufficient points in test gestures
- Check verbose output with `--info` flag

### Android Build

- Ensure Android SDK is installed
- Build tools version 29.0.2 required
- Target SDK 28

## Best Practices for AI Assistants

1. **Preserve Algorithm Accuracy**: Don't modify core algorithm constants without understanding mathematical implications
2. **Maintain Module Independence**: Keep `recognizers` module independent of client code
3. **Follow Kotlin Conventions**: Use data classes, immutability, and clear naming
4. **Test Recognition Changes**: Always test with `OneDollarTest` after modifying recognition logic
5. **Document New Gestures**: Add comments explaining gesture shape when adding to `StrokeFactory`
6. **Version Compatibility**: Maintain backward compatibility for library module
7. **Client Separation**: Changes to one client shouldn't affect others

## Additional Resources

- **Original Paper**: `/home/user/jesture/docs/dollar.pdf`
- **Project Page**: https://depts.washington.edu/aimgroup/proj/dollar/
- **Academic Paper**: http://faculty.washington.edu/wobbrock/pubs/uist-07.01.pdf

---

*This document was generated to help AI assistants understand and work effectively with the Jesture codebase. Last updated: 2025-11-16*
