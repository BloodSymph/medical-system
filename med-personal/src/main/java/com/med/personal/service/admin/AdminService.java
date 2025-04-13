package com.med.personal.service.admin;

import com.med.personal.dto.admin.MedPersonalAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Page<MedPersonalAdminResponse> getAllMedPersonalProfiles(Pageable pageable);

    Page<MedPersonalAdminResponse> searchMedPersonalProfiles(Pageable pageable, String searchText);

    MedPersonalAdminResponse getMedPersonalProfile(String username);

    void deleteMedPersonalProfile(String username, Long version);

}
