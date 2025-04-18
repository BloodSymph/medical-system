package com.company.patien.exeption.handler;

import com.company.patien.exeption.entity.ErrorEntity;
import com.company.patien.exeption.errors.InstrumentalExaminationsNotFoundException;
import com.company.patien.exeption.errors.InstrumentalExaminationsVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class InstrumentalExaminationsExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InstrumentalExaminationsNotFoundException.class)
    public ResponseEntity<ErrorEntity> instrumentalExaminationsNotFoundExceptionHandler(
            InstrumentalExaminationsNotFoundException instrumentalExaminationsNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(instrumentalExaminationsNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InstrumentalExaminationsVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> instrumentalExaminationsVersionNotValidExceptionHandler(
            InstrumentalExaminationsVersionNotValidException instrumentalExaminationsVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(instrumentalExaminationsVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
