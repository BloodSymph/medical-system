package com.company.patien.exeption.handler;

import com.company.patien.exeption.entity.ErrorEntity;
import com.company.patien.exeption.errors.PatientNotFoundException;
import com.company.patien.exeption.errors.PatientVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class PatientExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorEntity> patientNotFoundExceptionHandler(
            PatientNotFoundException patientNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(patientNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PatientVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> patientVersionNotValidExceptionHandler(
            PatientVersionNotValidException patientVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(patientVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
