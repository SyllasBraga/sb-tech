package com.sb.tech.services;

import com.sb.tech.api.response.JwtAuthenticationResponse;
import com.sb.tech.api.request.SigningRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signing(SigningRequest request);
}
