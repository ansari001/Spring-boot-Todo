package com.ansari.todo.mapper;

import com.ansari.todo.dto.auth.RegisterRequestDto;
import com.ansari.todo.dto.user.UserDto;
import com.ansari.todo.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);
}