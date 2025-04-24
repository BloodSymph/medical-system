package com.company.patien.service.client;

import com.company.patien.dto.client.PatientClientDetailsClientResponse;
import com.company.patien.dto.client.PatientClientRequest;
import com.company.patien.dto.client.PatientClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface PatientClientService {

    PatientClientResponse getPatientDetails();

    PatientClientDetailsClientResponse getPatientDetailsByUsername();

    PatientClientResponse createNewPatient(PatientClientRequest patientClientRequest);

    PatientClientResponse updatePatient(PatientClientRequest patientClientRequest);

    void deletePatient(Long version);

}
