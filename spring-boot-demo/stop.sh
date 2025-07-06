#!/bin/bash

# Stop the Spring Boot Todo List application

echo "Stopping Spring Boot Todo List application..."

# Check if PID file exists
if [ -f .pid ]; then
    PID=$(cat .pid)
    
    # Check if the process is still running
    if ps -p $PID > /dev/null; then
        echo "Stopping application with PID: $PID"
        kill $PID
        rm .pid
        echo "Application stopped successfully."
    else
        echo "Application is not running with PID: $PID"
        rm .pid
    fi
else
    # Try to find and kill any spring-boot processes
    PIDS=$(pgrep -f "spring-boot-demo")
    if [ -n "$PIDS" ]; then
        echo "Found running application processes: $PIDS"
        pkill -f "spring-boot-demo"
        echo "Application stopped successfully."
    else
        echo "No running application found."
    fi
fi
