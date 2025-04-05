package com.company.auth.repository;

import com.company.auth.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByNameIgnoreCase(String roleName);

    @Query("SELECT role FROM Role role WHERE LOWER(role.name) LIKE CONCAT('%', :searchText, '%') ")
    Page<RoleEntity> searchByText(
            @Param(value = "searchText") String searchText, Pageable pageable
    );

    Boolean existsByVersion(Long roleVersion);

    Boolean existsByNameIgnoreCase(String roleName);

    void deleteByNameIgnoreCase(String roleName);

}
