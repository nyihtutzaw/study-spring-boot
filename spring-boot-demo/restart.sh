#!/bin/bash

# Restart the Spring Boot Todo List application

echo "Restarting Spring Boot Todo List application..."

# First stop the application
./stop.sh

# Wait a moment to ensure clean shutdown
sleep 2

# Start the application again
./start.sh
