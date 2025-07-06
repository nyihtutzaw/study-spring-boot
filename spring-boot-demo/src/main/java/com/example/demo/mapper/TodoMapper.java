package com.example.demo.mapper;

import com.example.demo.dto.TodoDto;
import com.example.demo.dto.TodoRequest;
import com.example.demo.model.Todo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {

    public TodoDto toDto(Todo todo) {
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleted(todo.isCompleted());
        dto.setCreatedAt(todo.getCreatedAt());
        dto.setUpdatedAt(todo.getUpdatedAt());
        return dto;
    }

    public List<TodoDto> toDtoList(List<Todo> todos) {
        return todos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Todo toEntity(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());
        todo.setCreatedAt(new Date());
        todo.setUpdatedAt(new Date());
        return todo;
    }

    public void updateEntityFromRequest(Todo todo, TodoRequest request) {
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());
        todo.setUpdatedAt(new Date());
    }
}
