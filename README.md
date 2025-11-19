# WifiThings is a mobile app for remotely controlling smart devices, written in Kotlin for Ios and Android phones.
<img width="360" height="800" alt="image" src="https://github.com/user-attachments/assets/843cd050-8ed2-404d-bb9f-d93b00d4855f"/>

<img width="440" height="940" alt="image" src="https://github.com/user-attachments/assets/09707a8e-71b6-4d5e-ad0f-0322c695e3aa"/>

<img width="440" height="940" alt="image" src="https://github.com/user-attachments/assets/6548d64d-11eb-45e6-a14f-2523ade6fa4f"/>

The app allows you to control IoT devices over a Wi-Fi network. Currently, the app only allows you to turn devices on and off, but can be easily expanded with additional features.
The app uses ESP8266 microcontrollers as communication bridges, allowing you to create an affordable smart home system without investing in expensive commercial solutions.

✨ Key Features
🏠 Multi-Device Control - Add and manage multiple IoT devices
📱 Intuitive Interface - Modern Design with Material Design 3
⚡ Instant Response - Real-time control over WiFi
💾 Auto-Save - Devices remain remembered after closing the app
🌐 HTTP Communication - Simple REST API for easy integration

Technologies:   
- Kotlin Multiplatform
- Jetpack Compose 
- SQLDelight
- Koin
- OkHttp
- Kotlinx Coroutines / Flow
- Clean Architecture 
- MVVM

🔌 Hardware Requirements
ESP8266 (NodeMCU)
Relay 3.3V (SRD-05VDC-SL-C)
Power Supply - USB C or HiLink (HLK-PM01)
Control device/s

🔌Hardware Setup
1. Flash firmware ESP8266 from provided code
2. Connect the relay to the ESP8266 in ArduinoIDE - Check Serial Monitor to get ESP8266 IP
3. Connect the device with relay and relay with ESP8266  
4. Configure the device in the app
5. Enjoy !






This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
