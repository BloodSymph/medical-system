package com.med.personal.service.profile;

import com.med.personal.dto.user.MedPersonalRequest;
import com.med.personal.dto.user.MedPersonalResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalService {

    MedPersonalResponse getProfile();

    MedPersonalResponse createProfile(MedPersonalRequest medPersonalRequest);

    MedPersonalResponse updateProfile(MedPersonalRequest medPersonalRequest);

    void deleteProfile(String username, Long version);

}
