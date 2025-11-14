# GitHub Copilot Instructions for Android-Common-Utils19

## Project Overview

This is an Android utility library project targeting API 19 (KitKat) and above. It's a multi-module Gradle project with nested dependencies.

### Project Structure

- **common19**: Main library module (API 19+)
- **tests19**: Test application module
- **Utils17/common17**: Submodule for API 17 utilities
- **Utils16/common16**: Submodule for API 16 utilities
- **common14**: Base utilities for API 14
- **common_resources**: Shared resources

### Technology Stack

- **Language**: Kotlin (with some Java legacy code)
- **Build System**: Gradle with Android Gradle Plugin 8.12.0
- **Minimum SDK**: API 19 (Android 4.4 KitKat)
- **Target SDK**: API 34/35
- **JVM Target**: Java 17
- **Testing**: JUnit 4, AndroidX Test, Espresso

## Development Guidelines

### Code Standards

1. **Kotlin First**: Prefer Kotlin for new code; maintain existing Java code as-is unless refactoring
2. **Null Safety**: Leverage Kotlin's null safety features
3. **AndroidX**: Use AndroidX libraries, not legacy support libraries
4. **Min SDK Compatibility**: Ensure all code works on API 19+ devices
5. **MultiDex**: Project uses MultiDex for legacy Android support

### Build Configuration

#### Build Variants

The project uses flavor dimensions with the following structure:
- **Flavors**: development, production
- **Build Types**: debug, release, staging
- **Variant Filters**: Some combinations are excluded (e.g., developmentRelease, productionDebug)

#### Common Build Commands

```bash
# Build the project
./gradlew build

# Build specific module
./gradlew :Utils19:common19:build

# Run tests
./gradlew test

# Build debug APK for tests
./gradlew :tests19:assembleDevelopmentDebug

# Clean build
./gradlew clean build
```

### Testing Guidelines

1. **Test Framework**: Use JUnit 4 for unit tests
2. **Instrumentation Tests**: Use AndroidX Test and Espresso
3. **Test Location**: Unit tests in `src/test/`, instrumented tests in `src/androidTest/`
4. **Run Tests**: Execute `./gradlew test` before committing changes

### Dependencies

- **Firebase**: Firebase BOM 32.8.1 (Messaging, Firestore)
- **AndroidX**: AppCompat, ConstraintLayout, SwipeRefreshLayout, Core-KTX
- **Libraries**: CachePot, JavaTuples, iTextPDF, TableView
- **Module Dependencies**: common19 depends on common16 and common14

### Code Style

1. **Formatting**: Follow Kotlin coding conventions
2. **Naming**: Use camelCase for variables/functions, PascalCase for classes
3. **Comments**: Add KDoc comments for public APIs; keep inline comments minimal
4. **Imports**: Remove unused imports; organize imports logically

### Git Workflow

1. **Branch Naming**: Use descriptive names (e.g., `feature/add-xxxx`, `fix/issue-123`)
2. **Commits**: Write clear, concise commit messages
3. **Submodules**: This project uses git submodules; handle with care

### CI/CD

- Travis CI and Azure Pipelines are configured
- Builds must pass before merging
- Lint errors are non-blocking but should be addressed

### Common Tasks for Copilot

When working on issues, consider these guidelines:

#### Bug Fixes
- Identify the affected module (common19, common16, common14, etc.)
- Write or update tests to reproduce the bug
- Make minimal changes to fix the issue
- Verify the fix doesn't break existing functionality

#### Adding Features
- Determine the appropriate module for the feature
- Consider backward compatibility with API 19
- Add corresponding tests
- Update documentation if adding public APIs

#### Refactoring
- Maintain backward compatibility unless explicitly changing APIs
- Update all dependent modules if changing shared code
- Ensure all tests pass after refactoring
- Consider the impact on minimum SDK support

#### Dependency Updates
- Check compatibility with minSdkVersion 19
- Verify compatibility with existing Firebase BOM version
- Test thoroughly after dependency changes
- Update Gradle files consistently across modules

### Android-Specific Considerations

1. **Lifecycle Awareness**: Consider Activity/Fragment lifecycle when working with UI code
2. **Threading**: Use proper threading for background operations (avoid blocking main thread)
3. **Resource Management**: Be mindful of memory leaks and resource cleanup
4. **Permissions**: Handle runtime permissions appropriately for different API levels
5. **ProGuard**: Release builds use ProGuard; consider obfuscation implications

### Linting and Code Quality

- Lint checks are configured but set to non-blocking (`abortOnError false`)
- Address lint warnings when practical
- Follow Android best practices for performance and security

### When Stuck

1. Check existing similar implementations in the codebase
2. Review Android documentation for API-level-specific behavior
3. Consider asking for clarification on complex requirements
4. Test on multiple API levels when possible (especially API 19-21 vs modern versions)

### Module Dependency Chain

```
common19 → common16 → common14
                  ↓
           common_resources
```

When making changes, consider impacts up and down this dependency chain.

## Quick Reference

- **Minimum SDK**: 19
- **Target SDK**: 34/35  
- **Kotlin Version**: 2.2.0
- **AGP Version**: 8.12.0
- **Java Version**: 17
- **Primary Language**: Kotlin
- **Test Runner**: AndroidJUnitRunner

---

*These instructions help GitHub Copilot provide better assistance for this Android utility library project.*
