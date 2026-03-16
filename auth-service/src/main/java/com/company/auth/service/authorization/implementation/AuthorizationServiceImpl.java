package com.company.auth.service.authorization.implementation;

import com.company.auth.dto.authentication.AuthenticationResponse;
import com.company.auth.dto.authentication.ChangePasswordRequest;
import com.company.auth.dto.authentication.LoginRequest;
import com.company.auth.dto.authentication.RegisterRequest;
import com.company.auth.entity.RoleEntity;
import com.company.auth.entity.TokenEntity;
import com.company.auth.entity.UserEntity;
import com.company.auth.exception.exceptions.password.WrongPasswordException;
import com.company.auth.exception.exceptions.role.RoleNotFoundException;
import com.company.auth.exception.exceptions.user.UsernameIsTakenException;
import com.company.auth.repository.RoleRepository;
import com.company.auth.repository.TokenRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.service.jwt.JWTService;
import com.company.auth.service.authorization.AuthorizationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static com.company.auth.util.GetUserFromCurrentAuthSession.getSessionUser;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    @Override
    @Transactional
    public AuthenticationResponse registerNewUser(RegisterRequest registerRequest) {
        log.info("Checking if user exists: {}!", registerRequest.getUsername());
        if(userRepository.existsByUsernameIgnoreCase(registerRequest.getUsername())) {
            throw new UsernameIsTakenException(
                    "Username: " + registerRequest.getUsername() + " is taken!"
            );
        }
        log.info("Creating new user!");
        UserEntity user = new UserEntity();
        log.info("Set user name: {} ", registerRequest.getUsername());
        user.setUsername(registerRequest.getUsername());
        log.info("Set password: {} ",  registerRequest.getPassword());
        user.setPassword(passwordEncoder.encode(
                registerRequest.getPassword())
        );
        log.info("Set user version: {} ", registerRequest.getVersion());
        user.setVersion(registerRequest.getVersion());
        log.info("Find role by name: {}", registerRequest.getRoleName());
        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase(registerRequest.getRoleName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Role: " + registerRequest.getRoleName() + " not founded!"
                        )
                );
        log.info("Set role: {}", roleEntity);
        user.setRoles(List.of(roleEntity));
        log.info("Save user: {}", user);
        user = userRepository.save(user);
        log.info("Generated access token!");
        String accessToken = jwtService.generateAccessToken(user);
        log.info("Generated refresh token!");
        String refreshToken = jwtService.generateRefreshToken(user);
        log.info("Save user tokens!");
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "User registration was successful!"
        );

    }

    @Override
    @Transactional
    public AuthenticationResponse login(LoginRequest loginRequest) {
        log.info("Authenticate with authentication manager!");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        log.info("Find user by username: {}", loginRequest.getUsername());
        UserEntity user = userRepository
                .findByUsernameIgnoreCase(loginRequest.getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + loginRequest.getUsername() + "!"
                        )
                );
        log.info("Generated access token!");
        String accessToken = jwtService.generateAccessToken(user);
        log.info("Generated refresh token!");
        String refreshToken = jwtService.generateRefreshToken(user);
        log.info("Revoke all tokens by user: {}!", user);
        revokeAllTokenByUser(user);
        log.info("Save user tokens!");
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "User login was successful!"
        );

    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        log.info("Find user by username from authentication session!");
        UserEntity user = userRepository
                .findByUsernameIgnoreCase(getSessionUser())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                            "Can not find user by username: " + getSessionUser() + "!"
                        )
                );
        log.info("Matching password!");
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new WrongPasswordException("Wrong password!");
        }
        log.info("Confirm password!");
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new WrongPasswordException("Password is not the same!");
        }
        log.info("Set new password!");
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

       userRepository.save(user);

    }

    @Override
    @Transactional
    public ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {
        log.info("Get auth header!");
        String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Checking if auth header is bearer: {}", authHeader);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        log.info("Get string token!");
        String token = authHeader.substring(7);
        log.info("Get username from token!");
        String username = jwtService.extractUsername(token);
        log.info("Extract username: {}", username);
        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );
        if(jwtService.isValidRefreshToken(token, user)) {
            log.info("Generated access token!");
            String accessToken = jwtService.generateAccessToken(user);
            log.info("Generated refresh token!");
            String refreshToken = jwtService.generateRefreshToken(user);
            log.info("Revoke all tokens by user: {}!", user);
            revokeAllTokenByUser(user);
            log.info("Save user tokens!");
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity<>(
                    new AuthenticationResponse(
                            accessToken,
                            refreshToken,
                            "New token generated!"
                    ), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    private void revokeAllTokenByUser(UserEntity user) {
        log.info("Find all access tokens by userId: {}", user.getUsername());
        List<TokenEntity> validTokens = tokenRepository
                .findAllAccessTokenByUserId(user.getId());
        log.info("If validTokens is empty!");
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });
        log.info("Save all validTokens: {}!", validTokens);
        tokenRepository.saveAll(validTokens);

    }

    private void saveUserToken(
            String accessToken,
            String refreshToken,
            UserEntity user) {
        log.info("Create new user token!");
        TokenEntity token = new TokenEntity();
        log.info("Set new accessToken token: {}", accessToken);
        token.setAccessToken(accessToken);
        log.info("Set new refreshToken token: {}", refreshToken);
        token.setRefreshToken(refreshToken);
        log.info("Set loggedOut!");
        token.setLoggedOut(false);
        log.info("Set user tokens!");
        token.setUser(user);
        log.info("Save user tokens!");
        tokenRepository.save(token);

    }

}
