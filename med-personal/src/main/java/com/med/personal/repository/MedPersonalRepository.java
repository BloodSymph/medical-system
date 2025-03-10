package com.med.personal.repository;

import com.med.personal.entity.MedPersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedPersonalRepository extends JpaRepository<MedPersonalEntity, Long> {

    Optional<MedPersonalEntity> findByUsernameIgnoreCase(String username);

    void deleteByUsernameIgnoreCase(String username);
    
    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByVersion(Long version);

}
