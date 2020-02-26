package com.github.jonizei.clickergame.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

/**
 * This class holds information for the jwt token
 * It will read the values from application.properties file
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    /**
     * Secret key for the jwt token
     */
    private String secretKey;

    /**
     * Prefix for the jwt token
     */
    private String tokenPrefix;

    /**
     * Expiration time of the jwt token as days
     */
    private Integer tokenExpirationAfterDays;

    /**
     * Default constructor of JwtConfig
     */
    public JwtConfig() {

    }

    /**
     * Return value of the secret key
     *
     * @return Value of the secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Sets value for the secret key
     *
     * @param secretKey Value for the secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Returns value of a token prefix
     *
     * @return Value of a token prefix
     */
    public String getTokenPrefix() {
        return tokenPrefix;
    }

    /**
     * Set value for a token prefix
     *
     * @param tokenPrefix Value for a token prefix
     */
    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    /**
     * Returns expiration time as days
     *
     * @return Expiration time as days
     */
    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    /**
     * Set value for expiration days
     *
     * @param tokenExpirationAfterDays Value for expiration days
     */
    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    /**
     * Returns authorization header as String from
     * HttpHeader class
     *
     * @return Authorization header as String
     */
    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

}
