# Enday (2D Shooter Game)

Enday is a cross‑platform 2D shooter built with LibGDX. Jump into fast‑paced action, customize your character with blessings and buffs, and battle waves of enemies!

## Table of Contents
1. [Features](#features)  
2. [Prerequisites](#prerequisites)  
3. [Getting Started](#getting-started)  
4. [IDE Setup](#ide-setup)  
5. [Project Structure](#project-structure)  
6. [Configuration & Dependencies](#configuration--dependencies)  
7. [Controls](#controls)  
8. [Contributing](#contributing)  
9. [License](#license)  

## Features
- **Cross‑Platform**: Desktop (LWJGL3), HTML (GWT), iOS (RoboVM)  
- **Dynamic Abilities**: Unlock and stack Blessings and Buffs for varied playstyles  
- **Modular Architecture**: Core game logic separated from platform launchers  
- **Asset Management**: Uses LibGDX’s `AssetManager` for efficient loading and disposal  

## Prerequisites
- **Java Development Kit**: JDK 17 or later  
- **Git**: For cloning and version control (optional)  
- **Gradle Wrapper**: No global Gradle install needed (`gradlew`/`gradlew.bat`)  

## Getting Started

### Clone the repository
```bash
git clone https://github.com/tthegood/2DShooterGame.git
cd 2DShooterGame
Build & Run (Desktop)
bash
Copy
Edit
# Windows PowerShell
.\gradlew.bat desktop:run

# macOS / Linux
./gradlew desktop:run
This will download all dependencies (including LibGDX) and launch the desktop version.

IDE Setup
IntelliJ IDEA
File ▶ New ▶ Project from Existing Sources ▶ select build.gradle ▶ Finish

Eclipse
File ▶ Import ▶ Gradle ▶ Existing Gradle Project ▶ select project root

Gradle will configure module paths and fetch dependencies automatically.

Project Structure
pgsql
Copy
Edit
2DShooterGame/
├── core/       # Shared game logic (entities, screens, input, buffs)
├── desktop/    # Desktop launcher and LWJGL3 backend
├── html/       # GWT backend for browser deployment
├── ios/        # RoboVM backend for iOS
├── gradle/     # Gradle wrapper configuration
├── build.gradle        # Root build script (multi‑project)
├── settings.gradle     # Includes core, desktop, html, ios modules
├── gradlew, gradlew.bat# Gradle wrapper executables
└── LICENSE             # MIT License
Configuration & Dependencies
Module versions and dependencies are declared in the root build.gradle under shared ext properties:

LibGDX (gdxVersion), e.g. 1.11.0

Extensions: Box2D, Ashley, AI, Bullet, Box2DLights, Controllers, FreeType

Gradle pulls in the proper native libraries for each target automatically.

Controls
Action	Input
Move	Arrow keys / WASD
Shoot	Spacebar / Left‑click
Pause	P

Check MyInputProcessor.java for the full input mapping.

Contributing
Fork this repo and create a feature branch:

bash
Copy
Edit
git checkout -b feature/YourFeature
Implement your changes and commit:

bash
Copy
Edit
git add .
git commit -m "Add feature: YourFeature"
Push your branch and open a Pull Request on GitHub.

Please follow existing code style and write clear, descriptive commit messages.

License
This project is licensed under the MIT License.

makefile
Copy
Edit
::contentReference[oaicite:0]{index=0}