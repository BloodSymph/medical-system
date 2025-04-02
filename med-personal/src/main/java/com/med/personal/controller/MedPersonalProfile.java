package com.med.personal.controller;

import com.med.personal.dto.MedPersonalRequest;
import com.med.personal.dto.MedPersonalResponse;
import com.med.personal.service.MedPersonalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile-service")
public class MedPersonalProfile {

    private final MedPersonalService medPersonalService;

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public MedPersonalResponse getProfile(){
        return medPersonalService.getProfile();
    }

    @PostMapping("/profile/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalResponse createProfile(
            @Valid @RequestBody MedPersonalRequest medPersonalRequest){
        return medPersonalService.createProfile(medPersonalRequest);
    }

    @PutMapping("/profile/update")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalResponse updateProfile(
            @Valid @RequestBody MedPersonalRequest medPersonalRequest){
        return medPersonalService.updateProfile(medPersonalRequest);
    }

    @DeleteMapping("/profile/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProfile(
           @PathVariable(value = "username") String username,
           @RequestParam(value = "version", required = true) Long version) {
        medPersonalService.deleteProfile(username, version);
        return new ResponseEntity<>("Profile successful deleted!!", HttpStatus.OK);
    }



}
