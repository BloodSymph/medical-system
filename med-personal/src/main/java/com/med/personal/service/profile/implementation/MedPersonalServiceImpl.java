package com.med.personal.service.profile.implementation;

import com.med.personal.dto.client.MedPersonalRequest;
import com.med.personal.dto.client.MedPersonalResponse;
import com.med.personal.entity.MedPersonalEntity;
import com.med.personal.excepton.errors.MedPersonalProfileNotFoundException;
import com.med.personal.excepton.errors.MedPersonalVersionNotValidException;
import com.med.personal.repository.MedPersonalRepository;
import com.med.personal.service.profile.MedPersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.med.personal.mapper.MedPersonalMapper.mapMedPersonalRequestToEntity;
import static com.med.personal.mapper.MedPersonalMapper.mapMedPersonalResponse;
import static com.med.personal.util.GetUserFromCurrentAuthSession.getSessionUser;

@Service
@RequiredArgsConstructor
public class MedPersonalServiceImpl implements MedPersonalService {

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
            throw new MedPersonalVersionNotValidException(
                    "Med personal profile entity version not valid!"
            );
        }
        medPersonalEntity.setUsername(getSessionUser());
        medPersonalEntity.setFirstName(medPersonalRequest.getFirstName());
        medPersonalEntity.setLastName(medPersonalRequest.getLastName());
        medPersonalEntity.setPhoneNumber(medPersonalRequest.getPhoneNumber());
        medPersonalEntity.setAddress(medPersonalRequest.getAddress());
        medPersonalEntity.setEmail(medPersonalEntity.getEmail());
        medPersonalEntity.setSpecialty(medPersonalRequest.getSpecialty());
        medPersonalEntity.setSpecialtyCode(medPersonalRequest.getSpecialtyCode());
        medPersonalEntity.setVersion((medPersonalRequest.getVersion()));

        medPersonalRepository.save(medPersonalEntity);

        return mapMedPersonalResponse(medPersonalEntity);
    }

    @Override
    @Transactional
    public void deleteProfile(String username, Long version) {
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
        medPersonalRepository.deleteByUsernameIgnoreCase(username);
    }



}
