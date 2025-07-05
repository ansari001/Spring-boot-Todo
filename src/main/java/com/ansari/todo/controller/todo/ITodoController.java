package com.ansari.todo.controller.todo;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITodoController {

    ResponseEntity<ApiResponse<TodoDto>> createTodo(TodoDto dto);

    ResponseEntity<ApiResponse<List<TodoDto>>> getAllTodos();

    ResponseEntity<ApiResponse<TodoDto>> getTodoById(String todoId);

    ResponseEntity<ApiResponse<TodoDto>> updateTodoById(String todoId, TodoDto dto);

    ResponseEntity<ApiResponse<Void>> deleteTodoById(String todoId);
}