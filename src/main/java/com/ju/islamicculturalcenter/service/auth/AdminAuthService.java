package com.ju.islamicculturalcenter.service.auth;

import com.ju.islamicculturalcenter.dto.auth.AuthenticationResponse;
import com.ju.islamicculturalcenter.dto.auth.CreateAuthenticationRequest;
import com.ju.islamicculturalcenter.dto.auth.LogoutRequest;

public interface AdminAuthService {
    AuthenticationResponse login(CreateAuthenticationRequest authenticationRequest);

    void logout(LogoutRequest logoutRequest);
}
