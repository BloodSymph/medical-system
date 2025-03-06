package com.company.auth.exception.handler;

import com.company.auth.exception.entity.ErrorEntity;
import com.company.auth.exception.exceptions.user.UserNotFoundException;
import com.company.auth.exception.exceptions.user.UserVersionNotValidException;
import com.company.auth.exception.exceptions.user.UsernameIsTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorEntity> userNotFoundExceptionHandler(
            UserNotFoundException userNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(userNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(
                errorEntity, HttpStatus.NOT_FOUND
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> userVersionNotValidException(
            UserVersionNotValidException userVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(userVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(
                errorEntity, HttpStatus.BAD_REQUEST
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameIsTakenException.class)
    public ResponseEntity<ErrorEntity> usernameIsTakenExceptionHandler(
            UsernameIsTakenException usernameIsTakenException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(usernameIsTakenException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(
                errorEntity, HttpStatus.BAD_REQUEST
        );
    }

}
