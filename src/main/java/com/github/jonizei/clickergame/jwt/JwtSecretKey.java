package com.github.jonizei.clickergame.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * This class holds the SecretKey for jwt token.
 * The SecretKey is created using JwtConfig class
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@Configuration
@EnableConfigurationProperties(JwtConfig.class)
public class JwtSecretKey {

    /**
     * Class that holds information for a jwt token
     */
    private final JwtConfig jwtConfig;

    /**
     * Constructor of JwtSecretKey
     *
     * @param jwtConfig Class that holds information for a jwt token
     */
    @Autowired
    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * Return SecretKey object which will be created
     * using Keys.hmacShaKeyFor() method and secretKey string
     * from JwtConfig class
     *
     * @return SecretKey object for a jwt token
     */
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }

}
