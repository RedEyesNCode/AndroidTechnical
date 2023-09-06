# Android Project Readme

This repository contains an Android application that adheres to the following technical requirements:

## Technical Requirements

### Language and Testing
- The app is written in Kotlin.
- Unit tests are included to ensure code quality and functionality.

### Architecture
- The code follows the Model-View-ViewModel (MVVM) design pattern to promote separation of concerns and maintainability.

### Dependency Injection
- Dependency injection is implemented using either Koin or Hilt, providing flexibility and modularity in managing app dependencies.

### Network Calls
- Retrofit with Gson is used for making network calls, ensuring efficient data retrieval and serialization.

### Data Persistence
- Room is used for handling data persistence, enabling efficient storage and retrieval of app data.

### Reactive Programming
- Reactive programming is achieved using RxJava or Kotlin Coroutines and LiveData, enhancing the app's responsiveness and interactivity.

### Image Processing
- Image processing is done using Glide or Picasso, and a sample image is included in the "Drawable" resources folder.

### Task Scheduling
- Work Manager is preferred for scheduling tasks, allowing background processing and efficient task management.

### UI Tests (Optional)
- UI tests are provided as an optional extra to ensure the visual and functional correctness of the app.

### Remote Config
- Firebase Remote Config is utilized to manage the background color of screens within the application dynamically.

## Getting Started

To run and test the Android application, follow these steps:

1. Clone the repository to your local machine:

   ```
   git clone https://github.com/yourusername/your-repo.git](https://github.com/RedEyesNCode/AndroidTechnical.git)
   ```

2. Open the project in Android Studio.

3. Ensure you have the necessary dependencies and SDK components installed.

4. Build and run the app on your emulator or physical device.

5. Explore the app and its features, paying attention to the compliance with the specified technical requirements.

## Configuration

To manage the background color of screens using Firebase Remote Config, you'll need to set up Firebase and configure your project accordingly. Please follow the Firebase documentation for instructions on how to integrate Firebase Remote Config into your Android app.

## Contributors

- [Ashutosh Singh](https://github.com/RedEyesNCode) - Main developer and maintainer.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Special thanks to the contributors and open-source libraries that made this project possible.
