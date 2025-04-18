package com.company.patien.exeption.errors;

public class PatientVersionNotValidException extends RuntimeException {
    public PatientVersionNotValidException(String message) {
        super(message);
    }
}
