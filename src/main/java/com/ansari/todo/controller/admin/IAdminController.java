package com.ansari.todo.controller.admin;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.dto.user.UserDto;
import com.ansari.todo.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAdminController {

    ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers();

    ResponseEntity<ApiResponse<List<TodoDto>>> getAllTodosOfUser(String userId);

    ResponseEntity<ApiResponse<Void>> deleteUserById(String userId);

}
