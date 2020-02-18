package com.github.jonizei.clickergame.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jonizei.clickergame.applicationuser.ApplicationUser;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.util.Optional;

public class JwtLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public JwtLoginAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationUserRepository applicationUserRepository) {
        this.authenticationManager = authenticationManager;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            LoginAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String secretKey = "VerySecretKeyVerySecretKeyVerySecretKeyVerySecretKeyVerySecretKey";

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(10)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(authResult.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find user with username %s", authResult.getName())));

        response.addHeader("Authorization", "Bearer " + token);
        response.addIntHeader("Identification", applicationUser.getUserId());
    }
}
