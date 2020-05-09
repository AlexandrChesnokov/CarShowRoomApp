package com.alex.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
