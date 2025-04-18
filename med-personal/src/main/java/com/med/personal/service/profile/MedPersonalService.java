package com.med.personal.service.profile;

import com.med.personal.dto.client.MedPersonalRequest;
import com.med.personal.dto.client.MedPersonalResponse;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalService {

    MedPersonalResponse getProfile();

    MedPersonalResponse createProfile(MedPersonalRequest medPersonalRequest);

    MedPersonalResponse updateProfile(MedPersonalRequest medPersonalRequest);

    void deleteProfile(String username, Long version);

}
