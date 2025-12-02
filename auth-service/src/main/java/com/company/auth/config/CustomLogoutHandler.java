package com.company.auth.config;

import com.company.auth.entity.TokenEntity;
import com.company.auth.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        log.info("Get authorization header");
        String authHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authHeader);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        log.info(authHeader);
        String token = authHeader.substring(7);
        log.info("Get token: {}", token);
        TokenEntity storedToken = tokenRepository
                .findByAccessToken(token)
                .orElse(null);
        log.info("Found token: {}", storedToken);
        if(storedToken != null) {
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }

    }

}
