package com.ju.islamicculturalcenter.service.auth;

import com.ju.islamicculturalcenter.dto.auth.AuthenticationResponse;
import com.ju.islamicculturalcenter.dto.auth.CreateAuthenticationRequest;
import com.ju.islamicculturalcenter.dto.auth.CustomUserDetails;
import com.ju.islamicculturalcenter.entity.TokenBlackListEntity;
import com.ju.islamicculturalcenter.exceptions.AuthenticationException;
import com.ju.islamicculturalcenter.exceptions.CustomBadCredentialsException;
import com.ju.islamicculturalcenter.exceptions.UserNotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.repos.TokenBlackListRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserAuthServiceImpl implements UserAuthService{

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final TokenBlackListRepo tokenBlackListRepo;

    public UserAuthServiceImpl(AuthenticationManager authenticationManager, JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService, TokenBlackListRepo tokenBlackListRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.tokenBlackListRepo = tokenBlackListRepo;
    }

    @Override
    public AuthenticationResponse login(CreateAuthenticationRequest authenticationRequest) {
        requiredNonNUll(authenticationRequest.getUsername(), "username");
        requiredNonNUll(authenticationRequest.getPassword(), "password");

        String username = authenticationRequest.getUsername().toLowerCase();
        String password = authenticationRequest.getPassword();

        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        authenticate(username, password);
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
    public void logout(String token) {
        requiredNonNUll(token, "token");

        tokenBlackListRepo.save(TokenBlackListEntity.builder()
                        .active(true)
                        .createdById(UserDetailsUtil.getSuperAdminId())
                        .updatedById(UserDetailsUtil.getSuperAdminId())
                        .creationDate(new Timestamp(System.currentTimeMillis()))
                        .updateDate(new Timestamp(System.currentTimeMillis()))
                        .token(token)
                .build());
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
            throw new UserNotFoundException("Incorrect Username");
        }
    }

    private void requiredNonNUll(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()) {
            throw new ValidationException(name + " can't be empty!");
        }
    }
}
