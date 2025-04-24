package com.med.personal.mapper;

import com.med.personal.dto.client.MedPersonalClientRequest;
import com.med.personal.dto.client.MedPersonalClientResponse;
import com.med.personal.entity.MedPersonalEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalClientMapper {

    public static MedPersonalClientResponse mapMedPersonalResponse(
            MedPersonalEntity medPersonalEntity) {
        return MedPersonalClientResponse.builder()
                .id(medPersonalEntity.getId())
                .firstName(medPersonalEntity.getFirstName())
                .lastName(medPersonalEntity.getLastName())
                .username(medPersonalEntity.getUsername())
                .phoneNumber(medPersonalEntity.getPhoneNumber())
                .email(medPersonalEntity.getEmail())
                .address(medPersonalEntity.getAddress())
                .specialty(medPersonalEntity.getSpecialty())
                .specialtyCode(medPersonalEntity.getSpecialtyCode())
                .build();
    }

    public static MedPersonalEntity mapMedPersonalRequestToEntity(
            MedPersonalClientRequest medPersonalClientRequest) {
        return MedPersonalEntity.builder()
                .firstName(medPersonalClientRequest.getFirstName())
                .lastName(medPersonalClientRequest.getLastName())
                .phoneNumber(medPersonalClientRequest.getPhoneNumber())
                .email(medPersonalClientRequest.getEmail())
                .address(medPersonalClientRequest.getAddress())
                .specialty(medPersonalClientRequest.getSpecialty())
                .specialtyCode(medPersonalClientRequest.getSpecialtyCode())
                .version(medPersonalClientRequest.getVersion())
                .build();
    }

}