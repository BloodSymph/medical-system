package com.company.patien.controller;

import com.company.patien.service.client.InstrumentalExaminationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient-service/instrumental")
public class InstrumentalController {

    private final InstrumentalExaminationsService instrumentalExaminationsService;




}
