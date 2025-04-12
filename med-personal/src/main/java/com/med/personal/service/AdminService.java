package com.med.personal.service;

import com.med.personal.dto.admin.MedPersonalAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Page<MedPersonalAdminResponse> getAllMedPersonalProfiles(Pageable pageable);

    Page<MedPersonalAdminResponse> searchMedPersonalProfiles(Pageable pageable, String searchText);

    MedPersonalAdminResponse getMedPersonalProfile(String getBy);

    void deleteMedPersonalProfile(String deleteBy, Long version);

}
