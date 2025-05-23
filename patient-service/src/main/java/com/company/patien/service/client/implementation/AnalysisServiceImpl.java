package com.company.patien.service.client.implementation;

import com.company.patien.dto.client.AnalysisRequest;
import com.company.patien.dto.client.AnalysisResponse;
import com.company.patien.entity.AnalysisEntity;
import com.company.patien.entity.PatientEntity;
import com.company.patien.exeption.errors.AnalysisNotFoundException;
import com.company.patien.exeption.errors.AnalysisVersionNotValidException;
import com.company.patien.exeption.errors.PatientNotFoundException;
import com.company.patien.exeption.errors.PatientVersionNotValidException;
import com.company.patien.mapper.client.AnalysisClientMapper;
import com.company.patien.repository.AnalysisRepository;
import com.company.patien.repository.PatientRepository;
import com.company.patien.service.client.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.patien.mapper.client.AnalysisClientMapper.mapToAnalysisEntity;
import static com.company.patien.mapper.client.AnalysisClientMapper.mapToAnalysisResponse;


@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisRepository analysisRepository;

    private final PatientRepository patientRepository;

    @Override
    public List<AnalysisResponse> getAnalysis(String username) {
        return analysisRepository
                .findByAllPatientUsername(username)
                .stream()
                .map(AnalysisClientMapper::mapToAnalysisResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public AnalysisResponse createAnalysis(AnalysisRequest analysisRequest, String username) {
        PatientEntity patientEntity = patientRepository
                .findDetailsUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Cannot find patient with username: " + username + "!"
                        )
                );
        AnalysisEntity analysisEntity = mapToAnalysisEntity(analysisRequest);
        patientEntity.getAnalysis().add(analysisEntity);
        patientRepository.save(patientEntity);
        return mapToAnalysisResponse(analysisEntity);
    }


    @Override
    @Transactional
    public void deleteAnalysis(String username, Long version) {
        if (!analysisRepository.existsByPatientUsername(username)) {
            throw new AnalysisNotFoundException(
                    "Cannot find analysis with username: " + username + "!"
            );
        }
        if (!analysisRepository.existsByVersion(version)) {
            throw new AnalysisVersionNotValidException(
                    "Analysis version is not valid!"
            );
        }
        analysisRepository.deleteByUsernameIgnoreCase(username);
    }

}
