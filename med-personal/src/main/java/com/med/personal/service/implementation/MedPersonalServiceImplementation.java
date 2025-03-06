package com.med.personal.service.implementation;

import com.med.personal.dto.MedPersonalRequest;
import com.med.personal.dto.MedPersonalResponse;
import com.med.personal.service.MedPersonalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedPersonalServiceImplementation implements MedPersonalService {

    @Override
    public MedPersonalResponse getProfile() {
        return null;
    }

    @Override
    @Transactional
    public MedPersonalResponse createProfile(MedPersonalRequest medPersonalRequest) {
        return null;
    }

    @Override
    @Transactional
    public MedPersonalResponse updateProfile(MedPersonalRequest medPersonalRequest) {
        return null;
    }

    @Override
    @Transactional
    public void deleteProfile(String username, Long version) {

    }

}
