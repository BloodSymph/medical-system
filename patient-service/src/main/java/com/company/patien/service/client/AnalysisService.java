package com.company.patien.service.client;

import com.company.patien.dto.client.AnalysisRequest;
import com.company.patien.dto.client.AnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalysisService {

    List<AnalysisResponse> getAnalysis(String username);

    AnalysisResponse getAnalyseByUsername(String username);

    AnalysisResponse createAnalysis(AnalysisRequest analysisRequest, String username);

    void deleteAnalysis(String username, Long version);


}
