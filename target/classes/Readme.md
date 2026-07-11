
Smart Home Simulator

A JavaFX desktop application that simulates the management and automation of smart devices inside a modern home — built as a comprehensive Object-Oriented Programming (OOP) project.

The application allows users to control different rooms and smart devices such as lights, fans, doors, air conditioners, cameras, smoke sensors, and motion sensors from a single dashboard, while demonstrating core OOP principles in a realistic, functional desktop UI.


Project Overview

Smart Home Simulator mimics the behavior of real-world smart home systems like Google Home or Apple Home. Users can register/login, create rooms, add different types of devices to those rooms, control device states, set up automation rules (IF-trigger THEN-action), and monitor real-time energy consumption — all through a fully navigable multi-screen JavaFX interface.


Objectives


Implement and demonstrate all major OOP concepts in a real application
Build a modular, scalable Java application with clean package separation
Design a functional, styled JavaFX desktop interface
Simulate realistic smart home automation logic
Maintain clean, reusable, and testable code architecture



Tech Stack

TechnologyPurposeJava 21 (JDK 21)Core programming languageJavaFXDesktop GUI frameworkScene BuilderVisual UI design toolVS CodeIDEMavenBuild & dependency managementCSSJavaFX stylingGit & GitHubVersion control


OOP Concepts Demonstrated

ConceptWhere it's appliedEncapsulationPrivate fields with public getters/setters across all model and device classesAbstractionDevice abstract class defines a common contract for all smart devicesInheritanceLight, Fan, Door, Camera, AirConditioner, SmokeSensor, MotionSensor all extend DevicePolymorphismturnOn(), turnOff(), and displayStatus() behave differently for every device typeInterfacesSwitchable, Monitorable, Lockable define shared behavior contractsCompositionHouse owns Rooms, and Room owns Devices — deleting a Room deletes its DevicesAggregationHouse references Users, but Users can exist independently of a House


Application Screens


Login — Authenticate with a registered username/password
Sign Up — Register a new account (Name, Username, Email, Password)
Dashboard — Live overview: total rooms, total devices, online/offline device counts, quick navigation
Rooms — Add, remove, and browse rooms in the house
Devices — View and control all devices within a specific room (turn on/off, lock/unlock doors, adjust settings, add/remove devices)
All Devices — A consolidated view of every device across all rooms
Automation — Create IF-THEN automation rules (e.g., IF Motion Detected THEN Turn On Light) and evaluate them
Energy Monitor — Real-time power consumption breakdown per room and total household usage



Project Structure

SmartHomeSimulator/
│
├── src/main/java/app/
│   ├── Main.java                  # JavaFX application entry point
│   │
│   ├── controller/                # JavaFX FXML controllers
│   │   ├── LoginController.java
│   │   ├── SignUpController.java
│   │   ├── DashboardController.java
│   │   ├── RoomController.java
│   │   ├── DeviceController.java
│   │   ├── AllDevicesController.java
│   │   ├── AutomationController.java
│   │   └── EnergyController.java
│   │
│   ├── model/                     # Core domain models
│   │   ├── House.java
│   │   ├── Room.java
│   │   └── User.java
│   │
│   ├── devices/                   # Device hierarchy
│   │   ├── Device.java            # Abstract base class
│   │   ├── Light.java
│   │   ├── Fan.java
│   │   ├── Door.java
│   │   ├── Camera.java
│   │   ├── AirConditioner.java
│   │   ├── SmokeSensor.java
│   │   └── MotionSensor.java
│   │
│   ├── interfaces/                # Behavior contracts
│   │   ├── Switchable.java
│   │   ├── Monitorable.java
│   │   └── Lockable.java
│   │
│   ├── automation/                # Automation engine
│   │   ├── AutomationRule.java
│   │   ├── Schedule.java
│   │   └── DeviceManager.java
│   │
│   ├── service/                   # Supporting services
│   │   ├── EnergyCalculator.java
│   │   └── NotificationService.java
│   │
│   └── util/                      # Utilities
│       ├── SceneManager.java      # Screen navigation
│       └── AppContext.java        # Shared application state
│
├── src/main/resources/
│   ├── fxml/                      # Screen layouts
│   ├── css/style.css              # Application-wide styling
│   └── images/                    # Backgrounds, logo, assets
│
├── pom.xml
└── README.md


Core Features

✅ User registration & authentication
✅ Add / remove rooms dynamically
✅ Add / remove devices of 7 different types per room
✅ Turn devices ON/OFF with type-specific behavior (brightness, speed, lock state, recording, detection)
✅ Lock / unlock doors with state validation
✅ Simulate motion and smoke detection on sensors
✅ Create custom IF-THEN automation rules and execute them
✅ Real-time energy consumption tracking per room and household-wide
✅ Consolidated all-devices view across the entire house
✅ Dynamic, room-specific background imagery throughout the UI
✅ Custom-styled, responsive JavaFX interface with a consistent color theme



Getting Started

Prerequisites


JDK 21
Apache Maven
(Optional) Scene Builder for editing FXML visually


Run the application

bashgit clone https://github.com/<your-username>/SmartHomeSimulator.git
cd SmartHomeSimulator
mvn clean javafx:run






Author

Fizza
Software Engineering Undergraduate

License

This project was created for academic purposes as part of an Object-Oriented Programming course.