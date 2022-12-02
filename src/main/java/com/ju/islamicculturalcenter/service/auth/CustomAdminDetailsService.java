package com.ju.islamicculturalcenter.service.auth;

import com.ju.islamicculturalcenter.dto.auth.CustomUserDetails;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.repos.AdminRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAdminDetailsService implements UserDetailsService {
    private final AdminRepo adminRepo;

    public CustomAdminDetailsService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buildCustomUserDetailsOfUsername(username);
    }

    private CustomUserDetails buildCustomUserDetailsOfUsername(String username) {
        AdminEntity user = adminRepo.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Incorrect Username"));

        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUserName())
                .name(user.getUserName())
                .isEnabled(user.getIsActive())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getTitle())
                .password(user.getPassword())
                .roleGroup(user.getRole().getGroups().name())
                .build();
    }

}
