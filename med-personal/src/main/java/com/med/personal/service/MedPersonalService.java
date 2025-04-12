package com.med.personal.service;

import com.med.personal.dto.user.MedPersonalRequest;
import com.med.personal.dto.user.MedPersonalResponse;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalService {

    MedPersonalResponse getProfile();

    MedPersonalResponse createProfile(MedPersonalRequest medPersonalRequest);

    MedPersonalResponse updateProfile(MedPersonalRequest medPersonalRequest);

    void deleteProfile(String username, Long version);

}
