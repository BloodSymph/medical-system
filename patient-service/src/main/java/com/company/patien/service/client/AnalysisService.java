package com.company.patien.service.client;

import com.company.patien.dto.client.AnalysisRequest;
import com.company.patien.dto.client.AnalysisResponse;
import org.springframework.stereotype.Service;

@Service
public interface AnalysisService {

    AnalysisResponse getAnalysis(String username);

    AnalysisResponse createAnalysis(AnalysisRequest analysisRequest, String username);

    AnalysisResponse updateAnalysis(AnalysisRequest analysisRequest, String username);

    void deleteAnalysis(String username, Long version);


}
