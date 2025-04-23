package com.company.patien.mapper.admin;

import com.company.patien.dto.admin.AnalysisAdminResponse;
import com.company.patien.entity.AnalysisEntity;
import org.springframework.stereotype.Component;

@Component
public class AnalysisAdminMapper {

    public static AnalysisAdminResponse mapToAnalysisAdminResponse(AnalysisEntity analysisEntity) {
        return AnalysisAdminResponse.builder()
                .id(analysisEntity.getId())
                .analysisName(analysisEntity.getAnalysisName())
                .description(analysisEntity.getDescription())
                .created(analysisEntity.getCreated())
                .updated(analysisEntity.getUpdated())
                .version(analysisEntity.getVersion())
                .build();
    }

}
