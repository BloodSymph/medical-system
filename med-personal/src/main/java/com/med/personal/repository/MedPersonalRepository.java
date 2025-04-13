package com.med.personal.repository;

import com.med.personal.entity.MedPersonalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedPersonalRepository extends JpaRepository<MedPersonalEntity, Long> {

    Optional<MedPersonalEntity> findByUsernameIgnoreCase(String username);

    @Query("SELECT profile FROM Med_Personal profile " +
            "WHERE LOWER(profile.firstName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(profile.lastName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(profile.username) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(profile.email) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(profile.phoneNumber) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(profile.address) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(profile.specialty) LIKE CONCAT('%', :searchText, '%') ")
    Page<MedPersonalEntity> searchBy(
            Pageable pageable, @Param(value = "searchText") String searchText
    );

    void deleteByUsernameIgnoreCase(String username);
    
    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByVersion(Long version);

}
