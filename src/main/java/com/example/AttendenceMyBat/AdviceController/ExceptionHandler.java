package com.example.AttendenceMyBat.AdviceController;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler
    public Map<String, String> handleInvalidArgs(SQLIntegrityConstraintViolationException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler
    public Map<String, String> Jsonfromaterror(JsonParseException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Correct Your JSON Format");
        return error;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler
    public Map<String, String> invalidEmail(MethodArgumentNotValidException e) {
        Map<String, String> resp = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String feildname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(feildname, message);
        });
        return resp;
    }


}
