package com.ju.islamicculturalcenter.filter;

import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.repos.TokenBlackListRepo;
import com.ju.islamicculturalcenter.service.auth.CustomUserDetailsService;
import com.ju.islamicculturalcenter.service.auth.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ju.islamicculturalcenter.config.Config.USER_LOGIN_PATH;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    public static final String BEARER = "Bearer ";
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final TokenBlackListRepo tokenBlackListRepo;

    public CustomAuthorizationFilter(CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, TokenBlackListRepo tokenBlackListRepo) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.tokenBlackListRepo = tokenBlackListRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (!request.getServletPath().equals(USER_LOGIN_PATH) && authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {

            if(!tokenBlackListRepo.findAllByToken(authorizationHeader).isEmpty()){
                throw new NotFoundException("Token Not Valid");
            }

            String token = authorizationHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                String username = jwtUtil.getUsernameFromToken(token);
                if (username == null) {
                    throw new NotFoundException("user not found");
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}

