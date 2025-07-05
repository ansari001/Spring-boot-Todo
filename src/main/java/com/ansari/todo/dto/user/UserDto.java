package com.ansari.todo.dto.user;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
}
