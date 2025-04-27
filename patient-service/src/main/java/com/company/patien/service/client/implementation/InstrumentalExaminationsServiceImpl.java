package com.company.patien.service.client.implementation;

import com.company.patien.dto.client.InstrumentalExaminationsRequest;
import com.company.patien.dto.client.InstrumentalExaminationsResponse;
import com.company.patien.entity.InstrumentalExaminationsEntity;
import com.company.patien.entity.PatientEntity;
import com.company.patien.exeption.errors.InstrumentalExaminationsNotFoundException;
import com.company.patien.exeption.errors.InstrumentalExaminationsVersionNotValidException;
import com.company.patien.exeption.errors.PatientNotFoundException;
import com.company.patien.mapper.admin.InstrumentalExaminationsMapper;
import com.company.patien.mapper.client.InstrumentalExaminationClientMapper;
import com.company.patien.repository.InstrumentalExaminationsRepository;
import com.company.patien.repository.PatientRepository;
import com.company.patien.service.client.InstrumentalExaminationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.patien.mapper.client.InstrumentalExaminationClientMapper.mapToInstrumentalExaminationsEntity;
import static com.company.patien.mapper.client.InstrumentalExaminationClientMapper.mapToInstrumentalExaminationsResponse;

@Service
@RequiredArgsConstructor
public class InstrumentalExaminationsServiceImpl implements InstrumentalExaminationsService {

    private final InstrumentalExaminationsRepository instrumentalExaminationsRepository;

    private final PatientRepository patientRepository;

    @Override
    public List<InstrumentalExaminationsResponse> getInstrumentalExaminations(String username) {
        return instrumentalExaminationsRepository
                .findByPatientUsernameIgnoreCase(username)
                .stream()
                .map(InstrumentalExaminationClientMapper::mapToInstrumentalExaminationsResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InstrumentalExaminationsResponse createInstrumentalExaminations(
            InstrumentalExaminationsRequest instrumentalExaminationsRequest, String username) {
        PatientEntity patientEntity = patientRepository
                .findDetailsUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Cannot find patient with username: " + username + "!"
                        )
                );
        InstrumentalExaminationsEntity instrumentalExaminationsEntity = mapToInstrumentalExaminationsEntity(
                instrumentalExaminationsRequest
        );
        patientEntity.getInstrumentalExaminations().add(instrumentalExaminationsEntity);
        patientRepository.save(patientEntity);
        return mapToInstrumentalExaminationsResponse(instrumentalExaminationsEntity);
    }

    @Override
    @Transactional
    public void deleteInstrumentalExaminations(String username, Long version) {
        if (!instrumentalExaminationsRepository.existsByPatientUsername(username)) {
            throw new InstrumentalExaminationsNotFoundException(
                    "Cannot find instrumental examinations with username: " + username + "!"
            );
        }
        if (!instrumentalExaminationsRepository.existsByVersion(version)) {
            throw new InstrumentalExaminationsVersionNotValidException(
                    "Instrumental examinations version is not valid!"
            );
        }
        instrumentalExaminationsRepository.deleteByPatientUsernameIgnoreCase(username);
    }

}
