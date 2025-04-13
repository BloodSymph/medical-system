package com.med.personal.service.admin.implementetion;

import com.med.personal.dto.admin.MedPersonalAdminResponse;
import com.med.personal.entity.MedPersonalEntity;
import com.med.personal.excepton.errors.MedPersonalProfileNotFoundException;
import com.med.personal.excepton.errors.MedPersonalVersionNotValidException;
import com.med.personal.mapper.MedPersonalAdminMapper;
import com.med.personal.repository.MedPersonalRepository;
import com.med.personal.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.med.personal.mapper.MedPersonalAdminMapper.mapToAdminResponse;
import static com.med.personal.util.GetUserFromCurrentAuthSession.getSessionUser;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MedPersonalRepository medPersonalRepository;

    @Override
    public Page<MedPersonalAdminResponse> getAllMedPersonalProfiles(Pageable pageable) {
        return medPersonalRepository
                .findAll(pageable)
                .map(MedPersonalAdminMapper::mapToAdminResponse);
    }

    @Override
    public Page<MedPersonalAdminResponse> searchMedPersonalProfiles(
            Pageable pageable, String searchText) {
        return medPersonalRepository
                .searchBy(pageable, searchText)
                .map(MedPersonalAdminMapper::mapToAdminResponse);
    }

    @Override
    public MedPersonalAdminResponse getMedPersonalProfile(String username) {
        MedPersonalEntity medPersonalEntity = medPersonalRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Cannot find profile for username: " + username + "!"
                        )
                );
        return mapToAdminResponse(medPersonalEntity);
    }

    @Override
    @Transactional
    public void deleteMedPersonalProfile(String username, Long version) {
        if (!medPersonalRepository.existsByUsernameIgnoreCase(username)) {
            throw new MedPersonalProfileNotFoundException(
                    "Cannot find profile for username: " + username + "!"
            );
        }
        if(!medPersonalRepository.existsByVersion(version)) {
            throw new MedPersonalVersionNotValidException(
                    "Med personal profile entity version not valid!"
            );
        }
    }

}
