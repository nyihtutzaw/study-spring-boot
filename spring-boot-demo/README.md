# Spring Boot Todo List Application

A simple Todo List REST API built with Spring Boot and MySQL.

## Features

- Complete CRUD operations for Todo items
- MySQL database integration with JPA/Hibernate
- RESTful API design with proper DTO pattern
- Exception handling
- Search and filter capabilities

## Prerequisites

- Java 11+
- MySQL Server
- Maven (or use the included Maven wrapper)

## Database Configuration

The application uses a `.env` file to store sensitive database credentials. This keeps your credentials secure and out of version control.

1. Create a `.env` file in the root directory (a sample is provided)
2. Configure your database credentials in the `.env` file:


```properties
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/todo_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your_password_here
```


The application will automatically read these values from the `.env` file using the spring-dotenv library.

## Running the Application

### Using Maven

```bash
./mvnw spring-boot:run
```

### Using the provided scripts

The application comes with several utility scripts to make development easier:

1. **Start the application**:
   ```bash
   ./start.sh
   ```

2. **Stop the application**:
   ```bash
   ./stop.sh
   ```

3. **Restart the application**:
   ```bash
   ./restart.sh
   ```

4. **Run with hot reload** (watches for file changes):
   ```bash
   ./run-with-watch.sh
   ```

## API Endpoints

### Todo Operations

| Method | URL                         | Description                   | Request Body Example                                     |
|--------|-----------------------------|------------------------------ |----------------------------------------------------------|
| GET    | /api/todos                  | Get all todos                 | N/A                                                      |
| GET    | /api/todos?completed=true   | Get completed todos           | N/A                                                      |
| GET    | /api/todos?search=keyword   | Search todos                  | N/A                                                      |
| GET    | /api/todos/{id}             | Get todo by ID                | N/A                                                      |
| POST   | /api/todos                  | Create a new todo             | `{"title": "Task", "description": "Details", "completed": false}` |
| PUT    | /api/todos/{id}             | Update a todo                 | `{"title": "Updated", "description": "New details", "completed": true}` |
| DELETE | /api/todos/{id}             | Delete a todo                 | N/A                                                      |
| PATCH  | /api/todos/{id}/complete    | Toggle todo completion        | N/A                                                      |

## Example Requests

### Create a Todo

```bash
curl -X POST http://localhost:8081/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title": "Learn Spring Boot", "description": "Complete the Spring Boot tutorial", "completed": false}'
```

### Get All Todos

```bash
curl -X GET http://localhost:8081/api/todos
```

### Update a Todo

```bash
curl -X PUT http://localhost:8081/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"title": "Learn Spring Boot", "description": "Completed the Spring Boot tutorial", "completed": true}'
```

### Delete a Todo

```bash
curl -X DELETE http://localhost:8081/api/todos/1
```

## Development

The application uses Spring Boot DevTools for development-time features like hot reloading.

## License

This project is open source and available under the [MIT License](LICENSE).
