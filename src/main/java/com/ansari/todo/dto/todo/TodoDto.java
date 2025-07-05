package com.ansari.todo.dto.todo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TodoDto {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private String username;
    private String userId;
}
