# Little Lemon

Little lemon is the Capstone project for Meta's Android Developer Course on Coursera made in Kotlin.

## Screenshot
<img width="500" alt="Screenshot" src="https://github.com/sDevPrem/little-lemon/assets/130966261/4ce16a10-c0a4-43bb-b3f9-e8dc084bb154">

## Built With

**[Kotlin:](https://kotlinlang.org/)** As the programming language.

**[ktor:](https://ktor.io/)** For fetching data asynchronously from the server.

**[Jetpack Compose:](https://developer.android.com/jetpack/compose/documentation)** To make the UI.

**[Room:](https://developer.android.com/training/data-storage/room)** To cache the network data locally.

**[Glide Compose:](https://bumptech.github.io/glide/int/compose.html)** To load images asynchronously.

**[Jetpack Navigation:](https://developer.android.com/jetpack/compose/navigation)** For navigation between screens.

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
