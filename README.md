# SoftStep

## Overview

SoftStep is an innovative mobile application designed to assist patients with Parkinson's disease. The app provides a personalized exercise library tailored to each user's unique condition and needs, helping them maintain and improve their physical health.

## Features

- **Personalized Exercise Library**: Users input their condition details, and the app generates a custom exercise plan.
- **Dynamic Exercise Cards**: Exercises are displayed in visually appealing cards with images and descriptions.
- **Video Guides**: Embedded YouTube videos provide visual guidance for each exercise.
- **Profile Management**: Users can manage their profiles and update their condition details as needed.
- **Push Notifications**: Integrated Firebase Cloud Messaging to keep users informed and motivated.

## Tech Stack

- **Frontend**:
  - Android SDK
  - Java
  - XML for UI layouts
  - ConstraintLayout for responsive design
  - Material Components for modern UI elements
  
- **Backend**:
  - Firebase for cloud messaging and push notifications
  - SQLite for local data storage

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/SoftStep.git
    cd SoftStep
    ```

2. **Open the project in Android Studio**:
    - Open Android Studio.
    - Select "Open an existing Android Studio project".
    - Navigate to the cloned repository folder and select it.

3. **Add `google-services.json`**:
    - Go to the Firebase Console and create a new project (if you haven't already).
    - Add an Android app to your Firebase project.
    - Download the `google-services.json` file and place it in the `app` directory of your project.

4. **Build and Run**:
    - Connect your Android device or start an emulator.
    - Click the "Run" button in Android Studio.

## Usage

1. **Profile Setup**: On first launch, set up your profile by entering your condition details.
2. **Exercise Library**: Browse through your personalized exercise library. Click on any exercise card to view detailed instructions and video guides.
3. **Notifications**: Stay updated with reminders and motivational messages through push notifications.

## Screenshots
<img src="https://github.com/AbdulTur/SoftStep/assets/126967845/7809d711-2624-404c-a2e5-bee468e6d240" alt="Home Screen" width="200"/>
<img src="https://github.com/AbdulTur/SoftStep/assets/126967845/a833ab60-a797-4505-97aa-cf298e9fa46e" alt="Exercise Detail" width="200"/>
<img src="https://github.com/AbdulTur/SoftStep/assets/126967845/0fa62826-0e9e-4ca0-8e5a-1f0a2bc75e1a" alt="Profile Management" width="200"/>




## Contributing

We welcome contributions from the community! If you would like to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
    ```bash
    git checkout -b feature-name
    ```
3. Commit your changes.
    ```bash
    git commit -m "Add feature"
    ```
4. Push to your branch.
    ```bash
    git push origin feature-name
    ```
5. Create a pull request and describe your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or suggestions, feel free to open an issue or contact us at [aturonov@mun.ca](mailto:aturonov@mun.ca).

