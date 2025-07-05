package com.ansari.todo.controller.auth;

import com.ansari.todo.dto.auth.AuthRequestDto;
import com.ansari.todo.dto.auth.AuthResponseDto;
import com.ansari.todo.dto.auth.RegisterRequestDto;
import com.ansari.todo.payload.ApiResponse;
import com.ansari.todo.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements IAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registerUser")
    @Override
    public ResponseEntity<ApiResponse<AuthResponseDto>> registerUser(@RequestBody RegisterRequestDto dto) {
        AuthResponseDto authResponseDto = authService.registerUser(dto);
        return ResponseEntity.ok(new ApiResponse<>("User registered successfully", authResponseDto));
    }

    @PostMapping("/registerAdmin")
    @Override
    public ResponseEntity<ApiResponse<AuthResponseDto>> registerAdmin(@RequestBody RegisterRequestDto dto) {
        AuthResponseDto authResponseDto = authService.registerAdmin(dto);
        return ResponseEntity.ok(new ApiResponse<>("User registered successfully", authResponseDto));
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody AuthRequestDto dto) {
        AuthResponseDto authResponseDto = authService.login(dto);
        return ResponseEntity.ok(new ApiResponse<>("Login Success", authResponseDto));
    }

    @Override
    public void logout() {

    }
}