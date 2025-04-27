package com.company.patien.repository;

import com.company.patien.entity.InstrumentalExaminationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentalExaminationsRepository extends JpaRepository<InstrumentalExaminationsEntity, Long> {

    @Query("SELECT patients FROM Patient patients WHERE LOWER(patients.username) LIKE LOWER(:username) ")
    List<InstrumentalExaminationsEntity> findByPatientUsernameIgnoreCase(
            @Param(value = "username") String username
    );

    Boolean existsByVersion(Long version);

    @Query("SELECT patients FROM Patient patients WHERE LOWER(patients.username) LIKE LOWER(:username) ")
    Boolean existsByPatientUsername(@Param(value = "username") String username);

    @Modifying
    @Query("DELETE FROM Patient patients WHERE LOWER(patients.username) LIKE LOWER(:username) ")
    void deleteByPatientUsernameIgnoreCase(@Param(value = "username") String username);

}
