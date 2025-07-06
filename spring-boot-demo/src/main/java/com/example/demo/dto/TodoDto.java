package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Date createdAt;
    private Date updatedAt;
}
