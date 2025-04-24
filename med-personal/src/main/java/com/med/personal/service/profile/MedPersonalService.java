package com.med.personal.service.profile;

import com.med.personal.dto.client.MedPersonalClientRequest;
import com.med.personal.dto.client.MedPersonalClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalService {

    MedPersonalClientResponse getProfile();

    MedPersonalClientResponse createProfile(MedPersonalClientRequest medPersonalClientRequest);

    MedPersonalClientResponse updateProfile(MedPersonalClientRequest medPersonalClientRequest);

    void deleteProfile(String username, Long version);

}
