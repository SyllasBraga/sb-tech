package com.sb.tech.services;

import com.sb.tech.models.TechnicianModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);
    String generateToken(TechnicianModel userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
