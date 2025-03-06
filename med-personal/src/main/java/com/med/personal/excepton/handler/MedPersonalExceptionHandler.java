package com.med.personal.excepton.handler;

import com.med.personal.excepton.entity.ErrorEntity;
import com.med.personal.excepton.errors.MedPersonalProfileNotFoundException;
import com.med.personal.excepton.errors.MedPersonalVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class MedPersonalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MedPersonalProfileNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleMedPersonalProfileNotFoundException(
            MedPersonalProfileNotFoundException medPersonalProfileNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(medPersonalProfileNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MedPersonalVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> handleMedPersonalVersionNotValidException(
            MedPersonalVersionNotValidException medPersonalVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(medPersonalVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }



}
