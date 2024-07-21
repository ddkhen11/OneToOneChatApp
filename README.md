# WebSocket Chat Application

## Overview
This project is a real-time chat application built using Java, Spring Boot, and WebSocket technology. It enables instant messaging between users, efficient user management, and persistent storage of chat history.

## Features
- Real-time messaging using WebSocket
- Persistent storage of user profiles, chat rooms, and message history

## Setup and Installation
1. Ensure you have Java 17 and Maven installed on your system.
2. Clone the repository:
   ```
   git clone https://github.com/yourusername/websocket-chat-application.git
   ```
3. Navigate to the project directory:
   ```
   cd websocket-chat-application
   ```
4. Set up MongoDB:
   - You can use the provided `docker-compose.yaml` file to run MongoDB in a Docker container:
     ```
     docker-compose up -d
     ```
   - Alternatively, install and run MongoDB locally.

5. Configure the application:
   - Open `src/main/resources/application.yml`
   - Update the MongoDB connection details if necessary

6. Build the project:
   ```
   mvn clean install
   ```

7. Run the application:
   ```
   java -jar target/websocket-0.0.1-SNAPSHOT.jar
   ```

8. Access the application at `http://localhost:8088` in your web browser.
