package com.example.demo.controller;

import com.example.demo.dto.TodoDto;
import com.example.demo.dto.TodoRequest;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String search) {
        
        List<Todo> todos;
        
        if (completed != null) {
            todos = todoService.getTodosByStatus(completed);
        } else if (search != null && !search.isEmpty()) {
            todos = todoService.searchTodos(search);
        } else {
            todos = todoService.getAllTodos();
        }
        
        return new ResponseEntity<>(todoMapper.toDtoList(todos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        return new ResponseEntity<>(todoMapper.toDto(todo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoMapper.toEntity(todoRequest);
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(todoMapper.toDto(createdTodo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        Todo existingTodo = todoService.getTodoById(id);
        todoMapper.updateEntityFromRequest(existingTodo, todoRequest);
        Todo updatedTodo = todoService.updateTodo(id, existingTodo);
        return new ResponseEntity<>(todoMapper.toDto(updatedTodo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> toggleTodoCompletion(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        todo.setCompleted(!todo.isCompleted());
        Todo updatedTodo = todoService.updateTodo(id, todo);
        return new ResponseEntity<>(todoMapper.toDto(updatedTodo), HttpStatus.OK);
    }
}
