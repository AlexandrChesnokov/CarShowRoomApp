package com.alex.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.ws.rs.InternalServerErrorException;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(InternalServerErrorException.class)
    public String serverHandle(Exception ex) {
        return "errors/error500";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "errors/error404";
    }


}
