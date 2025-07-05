package com.ansari.todo.dto.auth;

import lombok.Data;

import java.util.List;

@Data
public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;
    List<String> roles;
}