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

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;

/**
 * This class handles requests to "/login" url
 * It will check if user is authenticated
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
public class JwtLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * AuthenticationManager for user authentication
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Class that holds information for jwt token
     */
    private final JwtConfig jwtConfig;

    /**
     * SecretKey object for jwt token
     */
    private final SecretKey secretKey;

    /**
     * Constructor of JwtLoginAuthenticationFilter
     *
     * @param authenticationManager AuthenticationManager for user authentication
     * @param jwtConfig Class that holds information for jwt token
     * @param secretKey SecretKey object for jwt token
     */
    public JwtLoginAuthenticationFilter(AuthenticationManager authenticationManager,
                                        JwtConfig jwtConfig,
                                        SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    /**
     * This method handles all login attempts
     *
     * It creates UsernameAndPasswordRequest object from client request
     * using ObjectMapper
     *
     * It creates Authentication object from UsernamePasswordAuthenticationToken
     * using values from UsernameAndPasswordRequest
     *
     * It retrieves Authentication from AuthenticationManager using created Authentication object
     * and then returns it
     *
     * @param request HttpServletRequest from a client
     * @param response HttpServletRespons for a client
     * @return Authentication object which holds information if a client is authenticated
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            UsernameAndPasswordRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * If authentication if a success then it will create a jwt token
     * using values from JwtConfig class.
     *
     * It will send jwt token to a client in a header value
     *
     * @param request HttpServletRequest from a client
     * @param response HttpServletResponse for a client
     * @param chain FilterChain object which holds all filters used in an application
     * @param authResult Result of the authenticationManager authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }
}
