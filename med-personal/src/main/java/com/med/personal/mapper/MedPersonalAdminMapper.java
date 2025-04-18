package com.med.personal.mapper;

import com.med.personal.dto.admin.MedPersonalAdminResponse;
import com.med.personal.entity.MedPersonalEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalAdminMapper {

    public static MedPersonalAdminResponse mapToAdminResponse(MedPersonalEntity medPersonalEntity) {
        return MedPersonalAdminResponse.builder()
                .id(medPersonalEntity.getId())
                .firstName(medPersonalEntity.getFirstName())
                .lastName(medPersonalEntity.getLastName())
                .username(medPersonalEntity.getUsername())
                .email(medPersonalEntity.getEmail())
                .phoneNumber(medPersonalEntity.getPhoneNumber())
                .address(medPersonalEntity.getAddress())
                .specialty(medPersonalEntity.getSpecialty())
                .specialtyCode(medPersonalEntity.getSpecialtyCode())
                .created(medPersonalEntity.getCreated())
                .updated(medPersonalEntity.getUpdated())
                .version(medPersonalEntity.getVersion())
                .build();
    }

}
