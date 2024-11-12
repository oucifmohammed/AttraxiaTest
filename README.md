# AttraxiaTest
## Architecture
- I tried to emulate a odularization approach inside the app module using packages.
- A “good modularization” is a components’ structure in which modules are highly cohesive and lowly coupled.
  - Modules are highly cohesive when they adhere to component cohesion principles.
  - Modules are lowly coupled when they adhere to component coupling principles.
- I followed the package by component approach
  - In package by component, you split the codebase into UI modules and component modules (domain layer + data layer).
  - By following this approach, we can reuse the component modules wihout depending on the UI of other features
  - The app module loads and connects all the features. For example : It adds Dependency injection code and navigation between features.
  ![components_architecture](https://github.com/user-attachments/assets/011efdc2-5f0f-4ccc-bbf3-71c6730cef53)

## UI
- I used Jetpack compose for the UI part of the app.
- I created a design system package that includes UI componets like the chat item and message item. This package also includes the theme of the app which consists of colors theme and typrography

## Dependency Injection
- I used Koin library for dependency injection instead of Dagger hilt to keep the door open for Kotlin Multiplatform.

## Coroutines and Kotlin Flow
- I used coroutines and Kotlin Flow to fetch and set-up the streams with Firebase Firestore.

## Running the app
- To run the app, you just need to open the project in Android Studio and run it.
