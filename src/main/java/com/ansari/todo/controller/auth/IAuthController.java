package com.ansari.todo.controller.auth;

import com.ansari.todo.dto.auth.AuthRequestDto;
import com.ansari.todo.dto.auth.AuthResponseDto;
import com.ansari.todo.dto.auth.RegisterRequestDto;
import com.ansari.todo.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthController {

    ResponseEntity<ApiResponse<AuthResponseDto>> registerUser(RegisterRequestDto dto);

    ResponseEntity<ApiResponse<AuthResponseDto>> registerAdmin(RegisterRequestDto dto);

    ResponseEntity<ApiResponse<AuthResponseDto>> login(AuthRequestDto dto);

    void logout();
}