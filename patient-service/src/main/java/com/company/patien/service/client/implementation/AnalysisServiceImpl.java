package com.company.patien.service.client.implementation;

import com.company.patien.dto.client.AnalysisRequest;
import com.company.patien.dto.client.AnalysisResponse;
import com.company.patien.repository.AnalysisRepository;
import com.company.patien.service.client.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisRepository analysisRepository;

    @Override
    public AnalysisResponse getAnalysis(String username) {
        return null;
    }

    @Override
    public AnalysisResponse createAnalysis(AnalysisRequest analysisRequest, String username) {
        return null;
    }

    @Override
    public AnalysisResponse updateAnalysis(AnalysisRequest analysisRequest, String username) {
        return null;
    }

    @Override
    public void deleteAnalysis(String username, Long version) {

    }

}
