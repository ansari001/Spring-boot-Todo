package com.ansari.todo.dto.auth;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}