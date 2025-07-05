package com.ansari.todo.util;

import com.ansari.todo.entity.User;
import com.ansari.todo.exception.ResourceNotFoundException;
import com.ansari.todo.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    @Autowired
    private UserRepository userRepository;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }
        return authentication.getName();
    }

    public String getCurrentUserId() {
        User user = userRepository.findByUsername(getCurrentUsername()).orElseThrow(() ->
                new ResourceNotFoundException("User not found."));
        return user.getId();
    }
}
