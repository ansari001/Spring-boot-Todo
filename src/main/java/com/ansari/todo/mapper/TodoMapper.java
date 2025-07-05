package com.ansari.todo.mapper;

import com.ansari.todo.dto.todo.TodoDto;
import com.ansari.todo.entity.Todo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto toDto(Todo todo);
    Todo toEntity(TodoDto dto);
    List<TodoDto> toDtoList(List<Todo> todoList);

}