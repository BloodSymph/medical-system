package com.company.auth.controller;

import com.company.auth.dto.authentication.AuthenticationResponse;
import com.company.auth.dto.authentication.ChangePasswordRequest;
import com.company.auth.dto.authentication.LoginRequest;
import com.company.auth.dto.authentication.RegisterRequest;
import com.company.auth.service.authorization.AuthorizationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse register(
            @Valid @RequestBody RegisterRequest registerRequest) {
        return authorizationService.registerNewUser(registerRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse login(
            @Valid @RequestBody LoginRequest loginRequest) {
        return authorizationService.login(loginRequest);
    }

    @PatchMapping("/change-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest, Principal principal) {
        authorizationService.changePassword(changePasswordRequest, principal);
        return new ResponseEntity<>(
                "Password successful changed!",
                HttpStatus.ACCEPTED
        );
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> refreshRequest(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {
        return authorizationService.refresh(
                httpServletRequest, httpServletResponse
        );
    }

}
