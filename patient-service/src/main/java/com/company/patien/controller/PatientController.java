package com.company.patien.controller;

import com.company.patien.service.client.PatientClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient-service/client")
public class PatientController {

    private final PatientClientService patientClientService;



}
