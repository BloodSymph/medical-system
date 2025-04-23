package com.company.patien.mapper.client;

import com.company.patien.dto.client.InstrumentalExaminationsRequest;
import com.company.patien.dto.client.InstrumentalExaminationsResponse;
import com.company.patien.entity.InstrumentalExaminationsEntity;
import org.springframework.stereotype.Component;

@Component
public class InstrumentalExaminationClientMapper {

    public static InstrumentalExaminationsResponse mapToInstrumentalExaminationsResponse(
            InstrumentalExaminationsEntity instrumentalExaminationsEntity) {
        return InstrumentalExaminationsResponse.builder()
                .id(instrumentalExaminationsEntity.getId())
                .instrumentalName(instrumentalExaminationsEntity.getInstrumentalName())
                .description(instrumentalExaminationsEntity.getDescription())
                .build();
    }

    public static InstrumentalExaminationsEntity mapToInstrumentalExaminationsEntity(
            InstrumentalExaminationsRequest instrumentalExaminationsRequest) {
        return InstrumentalExaminationsEntity.builder()
                .instrumentalName(instrumentalExaminationsRequest.getInstrumentalName())
                .description(instrumentalExaminationsRequest.getDescription())
                .build();
    }

}
