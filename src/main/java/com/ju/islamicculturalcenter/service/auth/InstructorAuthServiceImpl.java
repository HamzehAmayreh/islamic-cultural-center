package com.ju.islamicculturalcenter.service.auth;

import com.ju.islamicculturalcenter.dto.auth.AuthenticationResponse;
import com.ju.islamicculturalcenter.dto.auth.CreateAuthenticationRequest;
import com.ju.islamicculturalcenter.dto.auth.CustomUserDetails;
import com.ju.islamicculturalcenter.dto.auth.LogoutRequest;
import com.ju.islamicculturalcenter.exceptions.AuthenticationException;
import com.ju.islamicculturalcenter.exceptions.CustomBadCredentialsException;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InstructorAuthServiceImpl implements AdminAuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomInstructorDetailsService userDetailsService;
    private final JWTUtil jwtUtil;

    public InstructorAuthServiceImpl(AuthenticationManager authenticationManager, CustomInstructorDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthenticationResponse login(CreateAuthenticationRequest authenticationRequest) {
        requiredNonNUll(authenticationRequest.getUsername(), "username");
        requiredNonNUll(authenticationRequest.getPassword(), "password");

        String username = authenticationRequest.getUsername().toLowerCase();
        String password = authenticationRequest.getPassword();

        authenticate(username, password);
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .userId(userDetails.getId())
                .username(userDetails.getUsername())
                .name(userDetails.getName())
                .phoneNumber(userDetails.getPhoneNumber())
                .role(userDetails.getRole())
                .roleGroup(userDetails.getRoleGroup())
                .isEnabled(userDetails.getIsEnabled())
                .token("Bearer " + accessToken)
                .build();
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        requiredNonNUll(logoutRequest.getToken(), "token");
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled or email is unverified. " +
                    "Verify your email and connect with admin for more details. your username is " + username);
        } catch (BadCredentialsException e) {
            throw new CustomBadCredentialsException("Incorrect password");
        } catch (AuthenticationServiceException e) {
            throw new NotFoundException("Incorrect Username");
        }
    }

    private void requiredNonNUll(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()) {
            throw new ValidationException(name + " can't be empty!");
        }
    }
}
