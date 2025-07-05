package com.ansari.todo.service.todo;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.entity.Todo;
import com.ansari.todo.exception.ResourceNotFoundException;
import com.ansari.todo.mapper.TodoMapper;
import com.ansari.todo.repository.auth.UserRepository;
import com.ansari.todo.repository.todo.TodoRepository;
import com.ansari.todo.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private AuthUtil authUtil;


    @PreAuthorize("isAuthenticated()")
    @Override
    public TodoDto createTodo(TodoDto dto) {
        Todo todo = todoMapper.toEntity(dto);
        todo.setUserId(authUtil.getCurrentUserId());
        todo.setUsername(authUtil.getCurrentUsername());
        return todoMapper.toDto(todoRepository.save(todo));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todosList = todoRepository.findByUsername(authUtil.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("No data found for user: " + authUtil.getCurrentUsername()));
        return todoMapper.toDtoList(todosList);
    }

    @PreAuthorize("@todoSecurity.hasAccessToTodo(#todoId)")
    @Override
    public TodoDto getTodoById(String todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("No data found"));
        return todoMapper.toDto(todo);
    }

    @PreAuthorize("@todoSecurity.hasAccessToTodo(#todoId)")
    @Override
    public TodoDto updateTodoById(String todoId, TodoDto dto) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("No todo not found as per given Id"));

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());
        return todoMapper.toDto(todoRepository.save(todo));
    }

    @PreAuthorize("@todoSecurity.hasAccessToTodo(#todoId)")
    @Override
    public Void deleteTodoById(String todoId) {
        todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("No todo not found as per given Id"));
        todoRepository.deleteById(todoId);
        return null;
    }
}