package com.med.personal.service.profile.implementation;

import com.med.personal.dto.client.MedPersonalClientRequest;
import com.med.personal.dto.client.MedPersonalClientResponse;
import com.med.personal.entity.MedPersonalEntity;
import com.med.personal.excepton.errors.MedPersonalProfileNotFoundException;
import com.med.personal.excepton.errors.MedPersonalVersionNotValidException;
import com.med.personal.repository.MedPersonalRepository;
import com.med.personal.service.profile.MedPersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.med.personal.mapper.MedPersonalClientMapper.mapMedPersonalRequestToEntity;
import static com.med.personal.mapper.MedPersonalClientMapper.mapMedPersonalResponse;
import static com.med.personal.util.GetUserFromCurrentAuthSession.getSessionUser;

@Service
@RequiredArgsConstructor
public class MedPersonalServiceImpl implements MedPersonalService {

    private final MedPersonalRepository medPersonalRepository;


    @Override
    public MedPersonalClientResponse getProfile() {
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
    public MedPersonalClientResponse createProfile(MedPersonalClientRequest medPersonalClientRequest) {
        MedPersonalEntity medPersonalEntity = mapMedPersonalRequestToEntity(medPersonalClientRequest);
        medPersonalEntity.setUsername(getSessionUser());
        medPersonalRepository.save(medPersonalEntity);
        return mapMedPersonalResponse(medPersonalEntity);
    }

    @Override
    @Transactional
    public MedPersonalClientResponse updateProfile(MedPersonalClientRequest medPersonalClientRequest) {
        MedPersonalEntity medPersonalEntity = medPersonalRepository
                .findByUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Cannot find profile for username: " + getSessionUser() + "!"
                        )
                );
        if(!medPersonalEntity.getVersion().equals(medPersonalClientRequest.getVersion())) {
            throw new MedPersonalVersionNotValidException(
                    "Med personal profile entity version not valid!"
            );
        }
        medPersonalEntity.setUsername(getSessionUser());
        medPersonalEntity.setFirstName(medPersonalClientRequest.getFirstName());
        medPersonalEntity.setLastName(medPersonalClientRequest.getLastName());
        medPersonalEntity.setPhoneNumber(medPersonalClientRequest.getPhoneNumber());
        medPersonalEntity.setAddress(medPersonalClientRequest.getAddress());
        medPersonalEntity.setEmail(medPersonalEntity.getEmail());
        medPersonalEntity.setSpecialty(medPersonalClientRequest.getSpecialty());
        medPersonalEntity.setSpecialtyCode(medPersonalClientRequest.getSpecialtyCode());
        medPersonalEntity.setVersion((medPersonalClientRequest.getVersion()));

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
