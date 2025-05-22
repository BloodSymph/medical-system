package com.company.patien.controller;

import com.company.patien.dto.client.InstrumentalExaminationsRequest;
import com.company.patien.dto.client.InstrumentalExaminationsResponse;
import com.company.patien.service.client.InstrumentalExaminationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient-service/instrumental")
public class InstrumentalController {

    private final InstrumentalExaminationsService instrumentalExaminationsService;

    @GetMapping("/instrumental-examinations/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<InstrumentalExaminationsResponse> getInstrumentalExaminations(
            @PathVariable(value = "username") String username) {
        return instrumentalExaminationsService.getInstrumentalExaminations(username);
    }

    @PostMapping("/instrumental-examinations/{username}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentalExaminationsResponse createInstrumentalExaminations(
            @Valid @RequestBody InstrumentalExaminationsRequest instrumentalExaminationsRequest,
            @PathVariable(value = "username") String username) {
        return instrumentalExaminationsService.createInstrumentalExaminations(
                instrumentalExaminationsRequest, username
        );
    }

    @DeleteMapping("/instrumental-examinations/{username}/delete")
    public ResponseEntity<String> deleteInstrumentalExaminationsResponse(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "version", required = true) Long version) {
        instrumentalExaminationsService.deleteInstrumentalExaminations(username, version);
        return new ResponseEntity<>("Successful deleted", HttpStatus.OK);
    }

}
