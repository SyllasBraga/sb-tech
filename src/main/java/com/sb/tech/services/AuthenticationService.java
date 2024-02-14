package com.sb.tech.services;

import com.sb.tech.api.reponse.JwtAuthenticationResponse;
import com.sb.tech.api.request.SigningRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signing(SigningRequest request);
}
