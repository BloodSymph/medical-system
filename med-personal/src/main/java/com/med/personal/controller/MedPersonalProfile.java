package com.med.personal.controller;

import com.med.personal.dto.client.MedPersonalClientRequest;
import com.med.personal.dto.client.MedPersonalClientResponse;
import com.med.personal.service.profile.MedPersonalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile-service/client")
public class MedPersonalProfile {

    private final MedPersonalService medPersonalService;

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public MedPersonalClientResponse getProfile(){
        return medPersonalService.getProfile();
    }

    @PostMapping("/profile/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalClientResponse createProfile(
            @Valid @RequestBody MedPersonalClientRequest medPersonalClientRequest){
        return medPersonalService.createProfile(medPersonalClientRequest);
    }

    @PutMapping("/profile/update")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalClientResponse updateProfile(
            @Valid @RequestBody MedPersonalClientRequest medPersonalClientRequest){
        return medPersonalService.updateProfile(medPersonalClientRequest);
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
