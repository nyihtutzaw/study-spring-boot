#!/bin/bash

# Kill any existing Spring Boot processes
pkill -f spring-boot || true

# Clean the project first
echo "Cleaning the project..."
./mvnw clean

# Run Spring Boot with fork mode enabled
echo "Starting Spring Boot with hot reload..."
./mvnw spring-boot:run -Dspring-boot.run.fork=true -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true -Dspring.devtools.livereload.enabled=true"
