package com.company.patien.repository;

import com.company.patien.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Query("SELECT patient FROM Patient patient " +
            "WHERE LOWER(patient.firstName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(patient.lastName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(patient.username) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(patient.email) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(patient.phoneNumber) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(patient.address) LIKE CONCAT('%', :searchText, '%') " )
    Page<PatientEntity> searchPatients(String searchText, Pageable pageable);

    Optional<PatientEntity> findByUsernameIgnoreCase(String username);

    @EntityGraph(value = "patient-details-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT patient FROM Patient patient WHERE LOWER(patient.username) LIKE LOWER(:username) ")
    Optional<PatientEntity> findDetailsUsernameIgnoreCase(
            @Param(value = "username") String username
    );

    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByVersion(Long version);

    void deleteByUsernameIgnoreCase(String username);

}
