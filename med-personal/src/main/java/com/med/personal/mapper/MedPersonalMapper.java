package com.med.personal.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.med.personal.dto.MedPersonalRequest;
import com.med.personal.dto.MedPersonalResponse;
import com.med.personal.entity.MedPersonalEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalMapper {

    public static MedPersonalResponse mapMedPersonalResponse(
            MedPersonalEntity medPersonalEntity) {
        return MedPersonalResponse.builder()
                .firstName(medPersonalEntity.getFirstName())
                .lastName(medPersonalEntity.getLastName())
                .username(medPersonalEntity.getUsername())
                .phoneNumber(medPersonalEntity.getPhoneNumber())
                .address(medPersonalEntity.getAddress())
                .specialty(medPersonalEntity.getSpecialty())
                .specialtyCode(medPersonalEntity.getSpecialtyCode())
                .build();
    }

    public static MedPersonalEntity mapMedPersonalRequestToEntity(
            MedPersonalRequest medPersonalRequest) {
        return MedPersonalEntity.builder()
                .firstName(medPersonalRequest.getFirstName())
                .lastName(medPersonalRequest.getLastName())
                .phoneNumber(medPersonalRequest.getPhoneNumber())
                .address(medPersonalRequest.getAddress())
                .specialty(medPersonalRequest.getSpecialty())
                .specialtyCode(medPersonalRequest.getSpecialtyCode())
                .version(medPersonalRequest.getVersion())
                .build();
    }

}