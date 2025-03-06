package com.med.personal.excepton.errors;

public class MedPersonalProfileNotFoundException extends RuntimeException {
    public MedPersonalProfileNotFoundException(String message) {
        super(message);
    }
}
