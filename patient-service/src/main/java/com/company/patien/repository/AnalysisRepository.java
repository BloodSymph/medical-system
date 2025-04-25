package com.company.patien.repository;

import com.company.patien.entity.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long> {

    @Query("SELECT analysis FROM Analysis analysis " +
            "WHERE LOWER(analysis.patient.username) LIKE LOWER(:username)"
    )
    List<AnalysisEntity> findByAllPatientUsername(@Param(value = "username") String username);

    @Query("SELECT analysis FROM Analysis analysis " +
            "WHERE LOWER(analysis.patient.username) LIKE LOWER(:username) "
    )
    Optional<AnalysisEntity> getByUsername(@Param(value = "username") String username);

    Boolean existsByVersion(Long version);

    @Query("SELECT analysis FROM Analysis analysis " +
            "WHERE LOWER(analysis.patient.username) LIKE LOWER(:username) "
    )
    Boolean existsByPatientUsername(@Param(value = "username") String username);

    @Query("SELECT analysis FROM Analysis analysis " +
            "WHERE LOWER(analysis.patient.username) LIKE LOWER(:username) "
    )
    Optional<AnalysisEntity> getByPatientUsername(@Param(value = "username") String username);

    @Query("SELECT analysis FROM Analysis analysis " +
            "WHERE LOWER(analysis.patient.username) LIKE LOWER(:username) "
    )
    Boolean existsByUsername(@Param(value = "username") String username);

    @Modifying
    @Query("DELETE FROM Analysis analysis " +
            "WHERE LOWER(analysis.patient.username) LIKE LOWER(:username)"
    )
    void deleteByUsernameIgnoreCase(@Param(value = "username") String username);

}
