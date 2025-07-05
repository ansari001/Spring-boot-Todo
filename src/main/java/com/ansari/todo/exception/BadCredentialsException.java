package com.ansari.todo.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class BadCredentialsException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 2742216069043066973L;

    public BadCredentialsException(String msg) {
        super(msg);
    }

    public BadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
