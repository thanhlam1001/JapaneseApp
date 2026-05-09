# Kana Master

An Android app for learning Hiragana and Katakana with Kotlin and Jetpack Compose.

## Features

- Switch between `Hiragana` and `Katakana`
- Learn by 3 groups: `Basic`, `Dakuten`, and `Youon`
- Study each kana as a flash card
- Jump quickly through the full overview list
- Quiz mode for the currently selected group
- Built-in Japanese pronunciation using Android Text-to-Speech
- Mobile-friendly interface

## Structure

- `app/src/main/java/com/example/japanesealphabet/data/KanaRepository.kt`: kana data and learning groups
- `app/src/main/java/com/example/japanesealphabet/ui/KanaLearningApp.kt`: study UI, filters, quiz, and pronunciation
- `app/src/main/java/com/example/japanesealphabet/MainActivity.kt`: app entry point

## Open the project

1. Open the project folder in Android Studio, if available.
2. Let Gradle sync dependencies.
3. Run on an emulator or Android phone.

## Fastest build if your machine cannot run Android Studio

The fastest option is GitHub Actions. You do not need Android Studio, the JDK, or the Android SDK installed locally.

1. Create a new GitHub repository.
2. Push this project to that repository.
3. Open the `Actions` tab.
4. Run the `Android Build` workflow.
5. Download the `app-debug-apk` artifact after the workflow succeeds.
6. Extract the artifact and install `app-debug.apk` on your Android device.

## Notes

- This workspace does not currently include `gradle` or `gradlew`, so local build verification in this terminal is limited.
- Japanese pronunciation depends on the Japanese TTS voice available on the target device.
