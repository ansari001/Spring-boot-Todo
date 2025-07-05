package com.ansari.todo.service.todo;

import com.ansari.todo.dto.todo.TodoDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ITodoService {
    TodoDto createTodo(TodoDto dto);

    List<TodoDto> getAllTodos();

    TodoDto getTodoById(String todoId);

    TodoDto updateTodoById(String todoId, TodoDto dto);

    Void deleteTodoById(String todoId);
}
