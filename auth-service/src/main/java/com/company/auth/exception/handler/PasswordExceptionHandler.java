package com.company.auth.exception.handler;

import com.company.auth.exception.entity.ErrorEntity;
import com.company.auth.exception.exceptions.password.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class PasswordExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorEntity> wrongPasswordExceptionHandler(
            WrongPasswordException wrongPasswordException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(wrongPasswordException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
