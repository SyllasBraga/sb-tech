package com.sb.tech.services.impl;

import com.sb.tech.api.response.JwtAuthenticationResponse;
import com.sb.tech.api.request.SigningRequest;
import com.sb.tech.services.AuthenticationService;
import com.sb.tech.services.JwtService;
import com.sb.tech.services.TechnicianService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TechnicianService technicianService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(TechnicianService technicianService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.technicianService = technicianService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationResponse signing(SigningRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getDocument(), request.getPassword()));
        var technician = technicianService.getTechnicianByDocument(request.getDocument());
        var jwt = jwtService.generateToken(technician);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
