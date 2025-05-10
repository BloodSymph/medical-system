package com.company.patien.controller;

import com.company.patien.dto.admin.PatientAdminResponse;
import com.company.patien.repository.PatientRepository;
import com.company.patien.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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



}
