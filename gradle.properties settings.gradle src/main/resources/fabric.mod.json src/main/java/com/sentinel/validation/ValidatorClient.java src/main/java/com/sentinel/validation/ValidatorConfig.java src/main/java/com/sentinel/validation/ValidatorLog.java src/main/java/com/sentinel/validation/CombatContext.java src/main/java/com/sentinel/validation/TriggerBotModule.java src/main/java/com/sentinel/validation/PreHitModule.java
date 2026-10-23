# --- .github/workflows/build.yml ---
name: Build Jar

on:
  push:
    branches: [ main ]
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repo
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Grant execute permission for gradlew
        run: |
          if [ ! -f gradlew ]; then
            gradle wrapper --gradle-version 8.4
          fi
          chmod +x gradlew

      - name: Build with Gradle
        run: ./gradlew build

      - name: Upload jar artifact
        uses: actions/upload-artifact@v4
        with:
          name: anticheat-validator-jar
          path: build/libs/*.jar
