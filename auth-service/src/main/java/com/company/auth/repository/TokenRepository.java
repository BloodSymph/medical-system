package com.company.auth.repository;

import com.company.auth.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query("SELECT token FROM Token token " +
            "INNER JOIN User user ON token.user.id = user.id " +
            "WHERE token.user.id = :userId AND token.loggedOut = false"
    )
    List<TokenEntity> findAllAccessTokenByUserId(
            @Param("userId") Long userId
    );

    Optional<TokenEntity> findByAccessToken(String accessToken);

    Optional<TokenEntity> findByRefreshToken(String refreshToken);

}
