package com.company.patien.mapper.admin;

import com.company.patien.dto.admin.InstrumentalExaminationsAdminResponse;
import com.company.patien.entity.InstrumentalExaminationsEntity;
import org.springframework.stereotype.Component;

@Component
public class InstrumentalExaminationsMapper {

    public static InstrumentalExaminationsAdminResponse instrumentalExaminationsAdminResponse(
            InstrumentalExaminationsEntity instrumentalExaminationsEntity) {
        return InstrumentalExaminationsAdminResponse.builder()
                .id(instrumentalExaminationsEntity.getId())
                .instrumentalName(instrumentalExaminationsEntity.getInstrumentalName())
                .description(instrumentalExaminationsEntity.getDescription())
                .created(instrumentalExaminationsEntity.getCreated())
                .updated(instrumentalExaminationsEntity.getUpdated())
                .version(instrumentalExaminationsEntity.getVersion())
                .build();
    }

}
