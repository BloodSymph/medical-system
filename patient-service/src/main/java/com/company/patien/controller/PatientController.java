package com.company.patien.controller;

import com.company.patien.dto.client.PatientClientDetailsResponse;
import com.company.patien.dto.client.PatientClientRequest;
import com.company.patien.dto.client.PatientClientResponse;
import com.company.patien.service.client.PatientClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient-service/client")
public class PatientController {

    private final PatientClientService patientClientService;


    @GetMapping("/patient")
    @ResponseStatus(HttpStatus.OK)
    public PatientClientResponse getPatient() {
        return patientClientService.getPatient();
    }

    @GetMapping("/patient/details")
    @ResponseStatus(HttpStatus.OK)
    public PatientClientDetailsResponse getPatientDetails() {
        return patientClientService.getPatientDetailsByUsername();
    }

    @PostMapping("/patient/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientClientResponse createPatient(
            @Valid @RequestBody PatientClientRequest patientClientRequest) {
        return patientClientService.createNewPatient(patientClientRequest);
    }

    @PutMapping("/patient/update")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientClientResponse updatePatient(
            @Valid @RequestBody PatientClientRequest patientClientRequest) {
        return patientClientService.updatePatient(patientClientRequest);
    }

    @DeleteMapping("/patient/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePatient(
            @RequestParam(value = "version", required = true) Long version) {
        patientClientService.deletePatient(version);
        return new ResponseEntity<>("Successful deleted!", HttpStatus.OK);
    }

}
