package com.company.auth.repository;

import com.company.auth.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Boolean existsByUsernameIgnoreCase(String username);

    @EntityGraph(value = "user-roles-named-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT user FROM User user WHERE user.username LIKE LOWER(:username) ")
    Optional<UserEntity> getUserEntitiesByUsername(@Param(value = "username") String username);

    Boolean existsByVersion(Long userVersion);

    void deleteByUsername(String username);

}
