package com.med.personal.controller;

import com.med.personal.dto.admin.MedPersonalAdminResponse;
import com.med.personal.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile-service/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/profiles")
    @ResponseStatus(HttpStatus.OK)
    public Page<MedPersonalAdminResponse> getAllProfiles(
            @PageableDefault(
                    page = 0,
                    size = 25,
                    sort = "username",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        return adminService.getAllMedPersonalProfiles(pageable);
    }

    @GetMapping("/profiles/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<MedPersonalAdminResponse> searchProfile(
            @PageableDefault(
                    page = 0,
                    size = 25,
                    sort = "username",
                    direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(
                    value = "searchText",
                    defaultValue = "",
                    required = false) String searchText) {
        return adminService.searchMedPersonalProfiles(pageable, searchText);
    }

    @GetMapping("/profiles/{username}")
    @ResponseStatus(HttpStatus.OK)
    public MedPersonalAdminResponse getMedPersonalProfile(
            @PathVariable(value = "username") String username) {
        return adminService.getMedPersonalProfile(username);
    }

    @DeleteMapping("/profile/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteMedPersonalProfile(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "version") Long version) {
        adminService.deleteMedPersonalProfile(username, version);
        return new ResponseEntity<>(
                "Profile successful deleted!",
                HttpStatus.OK
        );
    }

}
