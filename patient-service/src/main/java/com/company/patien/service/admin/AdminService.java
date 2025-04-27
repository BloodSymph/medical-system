package com.company.patien.service.admin;

import com.company.patien.dto.admin.PatientAdminDetailsResponse;
import com.company.patien.dto.admin.PatientAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Page<PatientAdminResponse> getAllPatient(Pageable pageable);

    Page<PatientAdminResponse> searchPatients(String searchText, Pageable pageable);

    PatientAdminDetailsResponse getPatientsDetails(String username);

    void deletePatient(String username, Long version);

}
