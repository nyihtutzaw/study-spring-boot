#!/bin/bash

# Start the Spring Boot Todo List application

echo "Starting Spring Boot Todo List application..."

# Check if the application is already running
if pgrep -f "spring-boot-demo" > /dev/null; then
    echo "Application is already running. Use stop.sh to stop it first or restart.sh to restart it."
    exit 1
fi

# Run the application in the background
nohup ./mvnw spring-boot:run > app.log 2>&1 &

# Save the PID to a file for later use
echo $! > .pid
echo "Application started with PID: $(cat .pid)"
echo "Logs are being written to app.log"
echo "Access the application at http://localhost:8081/api/todos"
