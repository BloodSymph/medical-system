package com.med.personal.service.implementation;

import com.med.personal.dto.MedPersonalRequest;
import com.med.personal.dto.MedPersonalResponse;
import com.med.personal.entity.MedPersonalEntity;
import com.med.personal.excepton.errors.MedPersonalProfileNotFoundException;
import com.med.personal.repository.MedPersonalRepository;
import com.med.personal.service.MedPersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.med.personal.mapper.MedPersonalMapper.mapMedPersonalRequestToEntity;
import static com.med.personal.mapper.MedPersonalMapper.mapMedPersonalResponse;
import static com.med.personal.util.GetUserFromCurrentAuthSession.getSessionUser;

@Service
@RequiredArgsConstructor
public class MedPersonalServiceImplementation implements MedPersonalService {

    private final MedPersonalRepository medPersonalRepository;


    @Override
    public MedPersonalResponse getProfile() {
        MedPersonalEntity medPersonalEntity = medPersonalRepository
                .findByUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Cannot find profile for username: " + getSessionUser() + "!"
                        )
                );
        return mapMedPersonalResponse(medPersonalEntity);
    }

    @Override
    public MedPersonalResponse createProfile(MedPersonalRequest medPersonalRequest) {
        MedPersonalEntity medPersonalEntity = mapMedPersonalRequestToEntity(medPersonalRequest);
        medPersonalEntity.setUsername(getSessionUser());
        medPersonalRepository.save(medPersonalEntity);
        return mapMedPersonalResponse(medPersonalEntity);
    }

    @Override
    @Transactional
    public MedPersonalResponse updateProfile(MedPersonalRequest medPersonalRequest) {
        MedPersonalEntity medPersonalEntity = medPersonalRepository
                .findByUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Cannot find profile for username: " + getSessionUser() + "!"
                        )
                );
        if(!medPersonalEntity.getVersion().equals(medPersonalRequest.getVersion())) {
            throw new MedPersonalProfileNotFoundException(
                    "Med personal profile entity version not valid!"
            );
        }

        return null;
    }

    @Override
    @Transactional
    public void deleteProfile(String username, Long version) {

    }

}
