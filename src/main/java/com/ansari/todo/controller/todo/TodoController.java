package com.ansari.todo.controller.todo;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.payload.ApiResponse;
import com.ansari.todo.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController implements ITodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    @Override
    public ResponseEntity<ApiResponse<TodoDto>> createTodo(@RequestBody TodoDto dto) {
        return ResponseEntity.ok(new ApiResponse<>("Todo saved successfully!", todoService.createTodo(dto)));
    }

    @GetMapping("/getAllTodos")
    @Override
    public ResponseEntity<ApiResponse<List<TodoDto>>> getAllTodos() {
        return ResponseEntity.ok(new ApiResponse<>("All Todos", todoService.getAllTodos()));
    }

    @GetMapping("/getTodoById/{todoId}")
    @Override
    public ResponseEntity<ApiResponse<TodoDto>> getTodoById(@PathVariable String todoId) {
        return ResponseEntity.ok(new ApiResponse<>("", todoService.getTodoById(todoId)));
    }

    @PutMapping("/updateTodoById/{todoId}")
    @Override
    public ResponseEntity<ApiResponse<TodoDto>> updateTodoById(@PathVariable String todoId, @RequestBody TodoDto dto) {
        return ResponseEntity.ok(new ApiResponse<>("Todo is updated.", todoService.updateTodoById(todoId, dto)));
    }

    @DeleteMapping("/deleteTodoById/{todoId}")
    @Override
    public ResponseEntity<ApiResponse<Void>> deleteTodoById(@PathVariable String todoId) {
        return ResponseEntity.ok(new ApiResponse<>("Todo is deleted successfully!", todoService.deleteTodoById(todoId)));
    }
}