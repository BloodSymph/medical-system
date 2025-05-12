package com.company.patien.controller;

import com.company.patien.dto.admin.PatientAdminDetailsResponse;
import com.company.patien.dto.admin.PatientAdminResponse;
import com.company.patien.repository.PatientRepository;
import com.company.patien.service.admin.AdminService;
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
@RequestMapping("/api/v1/patient-service/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public Page<PatientAdminResponse> getAllPatient(@PageableDefault(
            page = 0,
            size = 25,
            sort = "username",
            direction = Sort.Direction.ASC) Pageable pageable) {
       return adminService.getAllPatient(pageable);
    }

    @GetMapping("/patients/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<PatientAdminResponse> searchPatient(@PageableDefault(
            page = 0,
            size = 25,
            sort = "username",
            direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(
                    value = "searchText", required = false, defaultValue = "") String searchText) {
        return adminService.searchPatients(searchText, pageable);
    }

    @GetMapping("/patients/{username}/details")
    @ResponseStatus(HttpStatus.OK)
    public PatientAdminDetailsResponse getPatientDetails(
            @PathVariable(value = "username") String username) {
        return adminService.getPatientsDetails(username);
    }

    @GetMapping("/patients/{username/delete}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePatient(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "version", required = true) Long version) {
        adminService.deletePatient(username, version);
        return new ResponseEntity<>("Successful deleted!", HttpStatus.OK);
    }

}
