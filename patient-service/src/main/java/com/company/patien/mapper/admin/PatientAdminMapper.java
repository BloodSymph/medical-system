package com.company.patien.mapper.admin;

import com.company.patien.dto.admin.PatientAdminDetailsResponse;
import com.company.patien.dto.admin.PatientAdminResponse;
import com.company.patien.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientAdminMapper {

    public static PatientAdminResponse mapToPatientAdminResponse(PatientEntity patientEntity) {
        return PatientAdminResponse.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .username(patientEntity.getUsername())
                .email(patientEntity.getEmail())
                .phoneNumber(patientEntity.getPhoneNumber())
                .address(patientEntity.getAddress())
                .created(patientEntity.getCreated())
                .updated(patientEntity.getUpdated())
                .version(patientEntity.getVersion())
                .build();
    }


    public static PatientAdminDetailsResponse mapToPatientAdminDetailsResponse(PatientEntity patientEntity) {
        return PatientAdminDetailsResponse.builder()
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
                                .map(AnalysisAdminMapper::mapToAnalysisAdminResponse)
                                .collect(Collectors.toList())
                )
                .instrumentalExaminations(
                    patientEntity.getInstrumentalExaminations()
                            .stream()
                            .map(InstrumentalExaminationsMapper::instrumentalExaminationsAdminResponse)
                            .collect(Collectors.toList())
                )
                .created(patientEntity.getCreated())
                .updated(patientEntity.getUpdated())
                .version(patientEntity.getVersion())
                .build();
    }

}
