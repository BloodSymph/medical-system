package com.company.patien.service.client;

import com.company.patien.dto.client.PatientClientDetailsResponse;
import com.company.patien.dto.client.PatientClientRequest;
import com.company.patien.dto.client.PatientClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface PatientClientService {

    PatientClientResponse getPatient();

    PatientClientDetailsResponse getPatientDetailsByUsername();

    PatientClientResponse createNewPatient(PatientClientRequest patientClientRequest);

    PatientClientResponse updatePatient(PatientClientRequest patientClientRequest);

    void deletePatient(Long version);

}
