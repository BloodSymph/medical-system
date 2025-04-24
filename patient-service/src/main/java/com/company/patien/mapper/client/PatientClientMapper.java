package com.company.patien.mapper.client;

import com.company.patien.dto.client.PatientClientDetailsClientResponse;
import com.company.patien.dto.client.PatientClientRequest;
import com.company.patien.dto.client.PatientClientResponse;
import com.company.patien.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientClientMapper {

    public static PatientClientResponse mapToPatientResponse(PatientEntity patientEntity) {
        return PatientClientResponse.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .username(patientEntity.getUsername())
                .email(patientEntity.getEmail())
                .phoneNumber(patientEntity.getPhoneNumber())
                .address(patientEntity.getAddress())
                .build();
    }

    public static PatientClientDetailsClientResponse mapToPatientDetailsResponse(PatientEntity patientEntity) {
        return PatientClientDetailsClientResponse.builder()
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

    public static PatientEntity mapToPatientEntity(PatientClientRequest patientClientRequest) {
        return PatientEntity.builder()
                .firstName(patientClientRequest.getFirstName())
                .lastName(patientClientRequest.getLastName())
                .email(patientClientRequest.getEmail())
                .phoneNumber(patientClientRequest.getPhoneNumber())
                .address(patientClientRequest.getAddress())
                .build();
    }

}
