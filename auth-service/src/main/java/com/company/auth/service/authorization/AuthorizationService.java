package com.company.auth.service.authorization;

import com.company.auth.dto.authentication.AuthenticationResponse;
import com.company.auth.dto.authentication.LoginRequest;
import com.company.auth.dto.authentication.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthorizationService {

    AuthenticationResponse registerNewUser(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response
    ) throws IOException;

}
