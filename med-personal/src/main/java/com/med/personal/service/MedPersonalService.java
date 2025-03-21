package com.med.personal.service;

import com.med.personal.dto.MedPersonalRequest;
import com.med.personal.dto.MedPersonalResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalService {

    MedPersonalResponse getProfile();

    MedPersonalResponse createProfile(MedPersonalRequest medPersonalRequest);

    MedPersonalResponse updateProfile(MedPersonalRequest medPersonalRequest);

    void deleteProfile(String username, Long version);

}
