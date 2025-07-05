package com.ansari.todo.service.auth;

import com.ansari.todo.dto.auth.AuthRequestDto;
import com.ansari.todo.dto.auth.AuthResponseDto;
import com.ansari.todo.dto.auth.RegisterRequestDto;

public interface IAuthService {

    AuthResponseDto registerUser(RegisterRequestDto dto);

    AuthResponseDto registerAdmin(RegisterRequestDto dto);

    AuthResponseDto login(AuthRequestDto dto);

    void logout();
}
