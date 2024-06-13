# Fetch Application

Fetch Application is an Android application designed to fetch and display hiring data from a remote JSON endpoint. It uses RecyclerView to display items grouped and sorted based on specified criteria.

## Features

- **Fetch Data:** Retrieves JSON data from a specified URL using OkHttp library.
- **Display:** Renders fetched data in a RecyclerView with grouping and sorting functionalities.
    - Display: All items are displayed grouped by their "listId".
    - Sorting: Items are sorted first by "listId" and then by "name".
    - Filtering: Items with blank or null "name" values are filtered out.
- **Filtering:** Allows users to filter displayed items based on item names.
- **Scroll to Top** Provides a floating action button to smoothly scroll to the top of the list.

## Screenshots

### Launch
![Screenshot_20240613-114557](https://github.com/SravyaL/FetchApp/assets/22209549/3c9c223a-869f-4785-be88-50559568dfd6)

### Search 
![Screenshot_20240613-114611](https://github.com/SravyaL/FetchApp/assets/22209549/d868d0c5-6960-491f-ada8-ff0000b4fbb9)

### Scroll to Top
![fetch_backtoTop](https://github.com/SravyaL/FetchApp/assets/22209549/2a54707d-11fd-462f-af45-c4eaaf86bf8a)



## Requirements

- Android SDK Version: API Level 21 (Android 5.0 Lollipop) and above.
- Minimum SDK Version: API Level 21.

## Installation

- Clone the repository and open the project in Android Studio.
- Build and run the application on an Android emulator or a physical device.

## Usage

1. **Launch Application:** Open the Fetch Application on your Android device.
2. **Fetch Data:** Upon launch, the application fetches JSON data from the remote endpoint.
3. **Display and Interaction:** View and interact with the displayed items in the RecyclerView.
4. **Filter Data:** Use the search bar to filter items based on their names.

## Testing

### Unit Tests

- **Purpose:** Ensure individual components and functions perform as expected.
- **Location:** Unit tests are located in the `app/src/test` directory.
- **Framework:** JUnit is used for unit testing.

### Integration Tests

- **Purpose:** Validate the interaction between integrated components.
- **Location:** Integration tests are located in the `app/src/androidTest` directory.
- **Framework:** Espresso is used for UI testing.


## Dependencies
### AndroidX Libraries
- androidx.appcompat:appcompat:1.5.1
- com.google.android.material:material:1.7.0

### Networking

- com.squareup.okhttp3:okhttp:4.9.2
- com.squareup.okhttp3:logging-interceptor:4.9.2
- com.android.volley:volley:1.2.0

### JSON Parsing
- com.google.code.gson:gson:2.8.6


## Acknowledgments
- Android Developers Documentation
- Open-source libraries used in this project

## Contact
For issues, questions, or suggestions related to Fetch Application, please contact [sravyalenka09@gmail.com]/[sravyal@umd.edu].

