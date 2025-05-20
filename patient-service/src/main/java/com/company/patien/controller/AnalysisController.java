package com.company.patien.controller;

import com.company.patien.dto.client.AnalysisRequest;
import com.company.patien.dto.client.AnalysisResponse;
import com.company.patien.service.client.AnalysisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient-service/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/patient-analysis/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnalysisResponse> getPatientAnalysis(
            @PathVariable(value = "username") String username) {
        return analysisService.getAnalysis(username);
    }

    @PostMapping("/patient-analysis/{username}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AnalysisResponse createPatientAnalysis(
            @Valid @RequestBody AnalysisRequest analysisRequest,
            @PathVariable(value = "username") String username) {
        return analysisService.createAnalysis(analysisRequest, username);
    }

    @DeleteMapping("/patient-analysis/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePatientAnalysis(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "version", required = true) Long version) {
        analysisService.deleteAnalysis(username, version);
        return new ResponseEntity<>("Successful deleted", HttpStatus.OK);
    }

}
