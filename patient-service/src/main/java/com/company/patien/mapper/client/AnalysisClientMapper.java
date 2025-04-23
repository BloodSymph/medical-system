package com.company.patien.mapper.client;

import com.company.patien.dto.client.AnalysisRequest;
import com.company.patien.dto.client.AnalysisResponse;
import com.company.patien.entity.AnalysisEntity;
import org.springframework.stereotype.Component;

@Component
public class AnalysisClientMapper {

    public static AnalysisResponse mapToAnalysisResponse(AnalysisEntity analysisEntity) {
        return AnalysisResponse.builder()
                .id(analysisEntity.getId())
                .analysisName(analysisEntity.getAnalysisName())
                .description(analysisEntity.getDescription())
                .build();
    }

    public static AnalysisEntity mapToAnalysisEntity(AnalysisRequest analysisRequest) {
        return AnalysisEntity.builder()
                .analysisName(analysisRequest.getAnalysisName())
                .description(analysisRequest.getDescription())
                .build();
    }

}
