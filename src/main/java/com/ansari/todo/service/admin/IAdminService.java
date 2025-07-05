package com.ansari.todo.service.admin;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.dto.user.UserDto;
import com.ansari.todo.entity.Todo;

import java.util.List;

public interface IAdminService {
    List<UserDto> getAllUsers();

    List<TodoDto> getAllTodosOfUser(String userId);

    Void deleteUserById(String userId);
}
