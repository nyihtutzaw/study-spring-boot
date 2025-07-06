package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TodoService {
    
    private static final String TODO_NOT_FOUND_MESSAGE = "Todo not found with id: ";

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TODO_NOT_FOUND_MESSAGE + id));
    }

    public List<Todo> getTodosByStatus(boolean completed) {
        return todoRepository.findByCompleted(completed);
    }

    public List<Todo> searchTodos(String keyword) {
        return todoRepository.findByTitleContaining(keyword);
    }

    public Todo createTodo(Todo todo) {
        todo.setCreatedAt(new Date());
        todo.setUpdatedAt(new Date());
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TODO_NOT_FOUND_MESSAGE + id));
        
        existingTodo.setTitle(todoDetails.getTitle());
        existingTodo.setDescription(todoDetails.getDescription());
        existingTodo.setCompleted(todoDetails.isCompleted());
        existingTodo.setUpdatedAt(new Date());
        return todoRepository.save(existingTodo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TODO_NOT_FOUND_MESSAGE + id));
        todoRepository.delete(todo);
    }
}
