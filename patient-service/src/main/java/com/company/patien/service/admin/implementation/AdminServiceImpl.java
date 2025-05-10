package com.company.patien.service.admin.implementation;

import com.company.patien.dto.admin.PatientAdminDetailsResponse;
import com.company.patien.dto.admin.PatientAdminResponse;
import com.company.patien.entity.PatientEntity;
import com.company.patien.exeption.errors.PatientNotFoundException;
import com.company.patien.exeption.errors.PatientVersionNotValidException;
import com.company.patien.mapper.admin.PatientAdminMapper;
import com.company.patien.repository.PatientRepository;
import com.company.patien.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.patien.mapper.admin.PatientAdminMapper.mapToPatientAdminDetailsResponse;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PatientRepository patientRepository;

    @Override
    public Page<PatientAdminResponse> getAllPatient(Pageable pageable) {
        return patientRepository
                .findAll(pageable)
                .map(PatientAdminMapper::mapToPatientAdminResponse);
    }

    @Override
    public Page<PatientAdminResponse> searchPatients(String searchText, Pageable pageable) {
        return patientRepository
                .searchPatients(searchText, pageable)
                .map(PatientAdminMapper::mapToPatientAdminResponse);
    }

    @Override
    public PatientAdminDetailsResponse getPatientsDetails(String username) {
        PatientEntity patientEntity = patientRepository
                .findDetailsUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Cannot find patient with username: " + username + "!"
                        )
                );
        return mapToPatientAdminDetailsResponse(patientEntity);
    }

    @Override
    @Transactional
    public void deletePatient(String username, Long version) {
        if (!patientRepository.existsByUsernameIgnoreCase(username)) {
            throw new PatientNotFoundException(
                    "Cannot find patient with username: " + username + "!"
            );
        }
        if (!patientRepository.existsByVersion(version)) {
            throw new PatientVersionNotValidException(
                    "Patient version not valid!"
            );
        }
        patientRepository.deleteByUsernameIgnoreCase(username);
    }

}
