# Little Lemon Restaurant App

## What it does:

This Android application allows users to explore the Little Lemon restaurant menu, view dishes with descriptions and prices, and filter the menu by category. It also includes basic user authentication and profile management features.

## What it is:

An Android mobile application developed using Kotlin.

## Screenshot
<img width="500" alt="Screenshot" src="https://github.com/sDevPrem/little-lemon/assets/130966261/4ce16a10-c0a4-43bb-b3f9-e8dc084bb154">

## Technologies Used:

* **[Kotlin:](https://kotlinlang.org/)** Primary programming language.
* **[ktor:](https://ktor.io/)** For network requests and data fetching.
* **[Room:](https://developer.android.com/training/data-storage/room)** For local data storage.
* **[Glide Compose:](https://bumptech.github.io/glide/int/compose.html)** To load images asynchronously.
* **[SharedPreferences:](https://developer.android.com/training/data-storage/shared-preferences)** For storing user preferences (e.g., login credentials).
* **[Jetpack Compose:](https://developer.android.com/jetpack/compose/documentation)** For building the user interface.
* **[Jetpack Navigation:](https://developer.android.com/jetpack/compose/navigation)** For navigation between screens.

## Ambition:

This project was undertaken to learn and practice key Android development concepts, including:

* **Modern Android development with Kotlin and Jetpack Compose.**
* **Data persistence using Room Database and SharedPreferences.**
* **Network requests and data handling with Ktor.**
* **User authentication and authorization.**
* **Implementing basic UI/UX design principles.**

## Current Stage:

This project is currently a proof of concept.

## Known Issues and Limitations:

* **Limited Functionality:** Currently, the app only supports user login, profile management, menu display, filtering, and basic searching. Features such as order placement, payment integration, and order tracking are not yet implemented.
* **Data Source:** The app currently uses static data for the menu. Integrating with a real-time database or API would enhance the application's functionality. 

## Installation

Simply clone this repository and open LittleLemon folder (android project folder) in android studio.

## Architecture

This app made using Android recommended [MVVM]("https://developer.android.com/topic/architecture") Architecture. Packages and their roles:

* `database` - local data base(Room)
* `network` - ktor Client
* `model` - data classes
* `ui` - It is UI layer which contains composable and navigation.

**Onboarding screen:** prompts users to enter their personal details.

**Stack navigation:** allows users to access the previous screen using the Back button.'

**Home screen:** features a header, hero section, menu breakdown section, and food menu list section.

**Profile screen:** displays the user's personal details.

Ability to save changes in the Profile screen that are retained when the app is restarted.
