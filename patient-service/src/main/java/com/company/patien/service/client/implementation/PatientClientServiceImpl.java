package com.company.patien.service.client.implementation;

import com.company.patien.dto.client.PatientClientDetailsClientResponse;
import com.company.patien.dto.client.PatientClientRequest;
import com.company.patien.dto.client.PatientClientResponse;
import com.company.patien.entity.PatientEntity;
import com.company.patien.exeption.errors.PatientNotFoundException;
import com.company.patien.exeption.errors.PatientVersionNotValidException;
import com.company.patien.repository.PatientRepository;
import com.company.patien.service.client.PatientClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.patien.mapper.client.PatientClientMapper.*;
import static com.company.patien.util.GetUserFromCurrentAuthSession.getSessionUser;

@Service
@RequiredArgsConstructor
public class PatientClientServiceImpl implements PatientClientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientClientResponse getPatient() {
        PatientEntity patientEntity = patientRepository
                .findByUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Cannot find patient with username: " + getSessionUser() + "!"
                        )
                );
        return mapToPatientResponse(patientEntity);
    }

    @Override
    public PatientClientDetailsClientResponse getPatientDetailsByUsername() {
        PatientEntity patientEntity = patientRepository
                .findDetailsUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Cannot find patient with username: " + getSessionUser() + "!"
                        )
                );
        return mapToPatientDetailsResponse(patientEntity);
    }

    @Override
    public PatientClientResponse createNewPatient(PatientClientRequest patientClientRequest) {
        PatientEntity patientEntity = mapToPatientEntity(patientClientRequest);
        patientEntity.setUsername(getSessionUser());
        patientRepository.save(patientEntity);
        return mapToPatientResponse(patientEntity);
    }

    @Override
    @Transactional
    public PatientClientResponse updatePatient(PatientClientRequest patientClientRequest) {
        PatientEntity patientEntity = patientRepository
                .findByUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Cannot find patient with username: " + getSessionUser() + "!"
                        )
                );
        if(!patientEntity.getVersion().equals(patientClientRequest.getVersion())) {
            throw new PatientVersionNotValidException(
                    "Patient version is not valid!"
            );
        }
        patientEntity.setFirstName(patientClientRequest.getFirstName());
        patientEntity.setLastName(patientClientRequest.getLastName());
        patientEntity.setUsername(getSessionUser());
        patientEntity.setEmail(patientClientRequest.getEmail());
        patientEntity.setPhoneNumber(patientClientRequest.getPhoneNumber());
        patientEntity.setAddress(patientClientRequest.getAddress());
        patientRepository.save(patientEntity);
        return mapToPatientResponse(patientEntity);
    }

    @Override
    @Transactional
    public void deletePatient(Long version) {
       if (!patientRepository.existsByUsernameIgnoreCase(getSessionUser())) {
           throw new PatientNotFoundException(
                   "Cannot find patient with username: " + getSessionUser() + "!"
           );
       }
       if(!patientRepository.existsByVersion(version)) {
           throw new PatientVersionNotValidException(
                   "Patient version is not valid!"
           );
       }
       patientRepository.deleteByUsernameIgnoreCase(getSessionUser());
    }

}
