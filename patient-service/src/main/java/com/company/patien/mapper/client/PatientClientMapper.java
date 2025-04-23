package com.company.patien.mapper.client;

import com.company.patien.dto.client.PatientDetailsResponse;
import com.company.patien.dto.client.PatientRequest;
import com.company.patien.dto.client.PatientResponse;
import com.company.patien.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientClientMapper {

    public static PatientResponse mapToPatientResponse(PatientEntity patientEntity) {
        return PatientResponse.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .username(patientEntity.getUsername())
                .email(patientEntity.getEmail())
                .phoneNumber(patientEntity.getPhoneNumber())
                .address(patientEntity.getAddress())
                .build();
    }

    public static PatientDetailsResponse mapToPatientDetailsResponse(PatientEntity patientEntity) {
        return PatientDetailsResponse.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .username(patientEntity.getUsername())
                .email(patientEntity.getEmail())
                .phoneNumber(patientEntity.getPhoneNumber())
                .address(patientEntity.getAddress())
                .analyses(
                        patientEntity.getAnalysis()
                                .stream()
                                .map(AnalysisClientMapper::mapToAnalysisResponse)
                                .collect(Collectors.toList())
                )
                .instrumentalExaminations(
                        patientEntity.getInstrumentalExaminations()
                                .stream()
                                .map(InstrumentalExaminationClientMapper::mapToInstrumentalExaminationsResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static PatientEntity mapToPatientEntity(PatientRequest patientRequest) {
        return PatientEntity.builder()
                .firstName(patientRequest.getFirstName())
                .lastName(patientRequest.getLastName())
                .email(patientRequest.getEmail())
                .phoneNumber(patientRequest.getPhoneNumber())
                .address(patientRequest.getAddress())
                .build();
    }

}
