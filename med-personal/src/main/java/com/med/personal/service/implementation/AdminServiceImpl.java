package com.med.personal.service.implementation;

import com.med.personal.dto.admin.MedPersonalAdminResponse;
import com.med.personal.mapper.MedPersonalAdminMapper;
import com.med.personal.repository.MedPersonalRepository;
import com.med.personal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private MedPersonalRepository medPersonalRepository;

    @Override
    public Page<MedPersonalAdminResponse> getAllMedPersonalProfiles(Pageable pageable) {
        return medPersonalRepository
                .findAll(pageable)
                .map(MedPersonalAdminMapper::mapToAdminResponse);
    }

    @Override
    public Page<MedPersonalAdminResponse> searchMedPersonalProfiles(
            Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public MedPersonalAdminResponse getMedPersonalProfile(String getBy) {
        return null;
    }

    @Override
    @Transactional
    public void deleteMedPersonalProfile(String deleteBy, Long version) {

    }

}
