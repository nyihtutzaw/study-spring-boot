#!/bin/bash

# This script provides a simple way to run Spring Boot and automatically rebuild when files change
# It uses a simple file watching approach that should work reliably

# Kill any existing Spring Boot processes
pkill -f spring-boot || true

# Clean the project
echo "Cleaning the project..."
./mvnw clean

# Initial build
echo "Building the project..."
./mvnw package -DskipTests

# Function to run the application
run_app() {
  echo "Starting Spring Boot application..."
  java -jar target/spring-boot-demo-0.0.1-SNAPSHOT.jar &
  APP_PID=$!
  echo "Application running with PID: $APP_PID"
}

# Function to rebuild and restart
rebuild_and_restart() {
  echo "Changes detected! Rebuilding..."
  
  # Kill the running application
  if [ ! -z "$APP_PID" ]; then
    echo "Stopping application with PID: $APP_PID"
    kill $APP_PID
  fi
  
  # Rebuild
  ./mvnw package -DskipTests
  
  # Run again
  run_app
}

# Start the application initially
run_app

# Watch for file changes
echo "Watching for file changes (Ctrl+C to stop)..."
while true; do
  sleep 2
  
  # Check if any Java files have changed
  CHANGES=$(find src -name "*.java" -newer target/spring-boot-demo-0.0.1-SNAPSHOT.jar 2>/dev/null)
  
  if [ ! -z "$CHANGES" ]; then
    echo "Detected changes in: $CHANGES"
    rebuild_and_restart
  fi
done
