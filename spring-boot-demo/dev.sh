#!/bin/bash

# This script provides a reliable way to run Spring Boot with automatic reloading

# Kill any existing Spring Boot processes
pkill -f spring-boot || true

# Clean and compile the project
echo "Cleaning and compiling the project..."
./mvnw clean compile

# Run the application with specific Java version
echo "Starting Spring Boot application..."
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.poll-interval=1s -Dspring.devtools.restart.quiet-period=400ms"
