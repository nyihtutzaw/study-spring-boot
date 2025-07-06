#!/bin/bash

# Kill any existing Spring Boot processes
pkill -f spring-boot

# Clean the application
echo "Cleaning the application..."
./mvnw clean

# Set JAVA_HOME to ensure consistent Java version
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@11/11.0.26/libexec/openjdk.jdk/Contents/Home

# Set environment variables for DevTools
export SPRING_DEVTOOLS_RESTART_ENABLED=true
export SPRING_DEVTOOLS_LIVERELOAD_ENABLED=true

# Run the application with DevTools enabled and fork mode
echo "Starting Spring Boot application with hot reload enabled..."
./mvnw spring-boot:run -Dspring-boot.run.fork=true
