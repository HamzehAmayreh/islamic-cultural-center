package com.ju.islamicculturalcenter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.filter.CustomAuthorizationFilter;
import com.ju.islamicculturalcenter.service.auth.CustomAdminDetailsService;
import com.ju.islamicculturalcenter.service.auth.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomAdminDetailsService userDetailsService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static final String ADMIN_LOGIN_PATH = "/api/v1/admin/auth/login";
    public static final String INSTRUCTOR_LOGIN_PATH = "/api/v1/instructor/auth/login";
    public static final String STUDENT_LOGIN_PATH = "/api/v1/student/auth/login";

    private static final String ADMIN_LOGOUT_PATH = "/api/v1/admin/auth/logout";
    private static final String INSTRUCTOR_LOGOUT_PATH = "/api/v1/instructor/auth/logout";
    private static final String STUDENT_LOGOUT_PATH = "/api/v1/student/auth/logout";
    private static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH");
    private static final List<String> ALLOWED_HEADERS = Arrays.asList("x-requested-with", "authorization", "Content-Type",
            "Authorization", "credential", "X-XSRF-TOKEN", "X-Refresh-Token", "X-Client-Id", "x-client-id");

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> accessDenied(response))
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .cors().configurationSource(request -> getCorsConfiguration())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ADMIN_LOGIN_PATH).permitAll()
                .antMatchers(INSTRUCTOR_LOGIN_PATH).permitAll()
                .antMatchers(STUDENT_LOGIN_PATH).permitAll()

                .antMatchers(ADMIN_LOGOUT_PATH).permitAll()
                .antMatchers(INSTRUCTOR_LOGOUT_PATH).permitAll()
                .antMatchers(STUDENT_LOGOUT_PATH).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(new CustomAuthorizationFilter(userDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
        corsConfiguration.setAllowedMethods(ALLOWED_METHODS);
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    private void accessDenied(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(Response.builder()
                .code(CODE.FORBIDDEN.getId())
                .message("Access denied")
                .success(false)
                .build()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
