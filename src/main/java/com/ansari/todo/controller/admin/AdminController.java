package com.ansari.todo.controller.admin;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.dto.user.UserDto;
import com.ansari.todo.payload.ApiResponse;
import com.ansari.todo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController implements IAdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>("All Users", adminService.getAllUsers()));
    }

    @GetMapping("/getAllTodosOfUser/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ApiResponse<List<TodoDto>>> getAllTodosOfUser(@PathVariable String username) {
        return ResponseEntity.ok(new ApiResponse<>(username, adminService.getAllTodosOfUser(username)));
    }

    @DeleteMapping("/deleteUserById/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ApiResponse<Void>> deleteUserById(@PathVariable String userId) {
        return ResponseEntity.ok(new ApiResponse<>("User deleted successfully",
                adminService.deleteUserById(userId)));
    }
}