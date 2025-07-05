package com.ansari.todo.service.todo;

import com.ansari.todo.repository.todo.TodoRepository;
import com.ansari.todo.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("todoSecurity")
public class TodoSecurity {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AuthUtil authUtil;

    public boolean hasAccessToTodo(String todoId) {
       // String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return todoRepository.findById(todoId)
                .map(todo -> todo.getUserId().equals(authUtil.getCurrentUserId()))
                .orElse(false);
    }
}
