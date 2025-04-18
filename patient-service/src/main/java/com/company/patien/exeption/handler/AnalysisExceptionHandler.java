package com.company.patien.exeption.handler;

import com.company.patien.exeption.entity.ErrorEntity;
import com.company.patien.exeption.errors.AnalysisNotFoundException;
import com.company.patien.exeption.errors.AnalysisVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AnalysisExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AnalysisNotFoundException.class)
    public ResponseEntity<ErrorEntity> analysisNotFoundExceptionHandler(
            AnalysisNotFoundException analysisNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(analysisNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AnalysisVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> analysisVersionNotValidExceptionHandler(
            AnalysisVersionNotValidException analysisVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(analysisVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
