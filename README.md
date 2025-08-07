3D Engine
Overview

3D Engine is a Java-based interactive rendering engine that visualizes and manipulates 3D objects in real time. Designed with education in mind, this application demonstrates core computer graphics concepts such as 3D-to-2D projection, geometric transformations, and user-driven object rotation using keyboard input.

The engine renders wireframe triangular meshes, providing a transparent view of geometric structures while maintaining interactive performance.
✨ Key Features
Feature	Description
3D Object Rendering	Displays 3D objects as wireframe triangular meshes
Real-time Interaction	Rotate 3D objects using arrow keys (X and Y axis rotation)
Multiple Shapes	Supports cubes, pyramids, and composite Rubik's cubes
Mathematical Core	Implements 4×4 matrix transformations and 3D-to-2D projection
MVC Architecture	Clean separation between model, view, and controller logic

By default, the application launches with a fully constructed 3x3x3 Rubik’s cube composed of 27 smaller cubes.
🧱 System Architecture

The 3D Engine follows the Model-View-Controller (MVC) pattern:

    Model: Manages 3D geometry and transformation logic

    View: Renders 3D scenes using Graphics2D

    Controller: Handles user input for rotating the object

Each component is independently managed, promoting modularity and ease of extension.
🚀 Application Lifecycle

    Initialization:

        Instantiates the MVC components

        Injects dependencies and wires them together

        Configures the window using JFrame

    Execution:

        Enters the rendering loop

        Updates object rotation based on keyboard input

        Continuously repaints the view for real-time interaction

Entry point:
src/main/java/Main/Main.java (lines 8–31)
🔧 Technology Stack
Component	Technology	Purpose
Language	Java 17	Core implementation language
Build System	Maven	Project management and dependency handling
UI Framework	Java Swing	Window and interface components
Graphics	Java AWT (Graphics2D)	2D rendering of 3D objects as wireframes
Architecture	MVC Pattern	Clean separation of concerns

Maven coordinates:

<groupId>erick.labrada</groupId>  
<artifactId>3dEngine</artifactId>  

🗂️ Project Structure

Organized by responsibility and architecture:

src/
└── main/
    └── java/
        └── Main/             # Application bootstrap and main class
        └── Model/            # 3D object representation and transformation
        └── View/             # Rendering logic using Swing/AWT
        └── Controller/       # Input handling and scene updates

This structure enables easy expansion with new shapes, rendering styles, or interaction types.
🏁 Getting Started
Prerequisites

    Java 17

    Maven 3+

Build & Run

git clone https://github.com/yourusername/3d-engine.git
cd 3d-engine
mvn clean compile exec:java -Dexec.mainClass="Main.Main"
