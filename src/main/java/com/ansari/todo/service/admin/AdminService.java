package com.ansari.todo.service.admin;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.dto.user.UserDto;
import com.ansari.todo.entity.Todo;
import com.ansari.todo.entity.User;
import com.ansari.todo.exception.ResourceNotFoundException;
import com.ansari.todo.mapper.TodoMapper;
import com.ansari.todo.mapper.UserMapper;
import com.ansari.todo.repository.auth.UserRepository;
import com.ansari.todo.repository.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TodoMapper todoMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        return userMapper.toDtoList(userList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<TodoDto> getAllTodosOfUser(String username) {
        List<Todo> userList = todoRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No todos found for user: " + username));
        return todoMapper.toDtoList(userList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Void deleteUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        userRepository.delete(user);
        return null;
    }
}
