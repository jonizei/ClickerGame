package com.github.jonizei.clickergame.jwt;

/**
 * This class holds username and password information of a user
 * It will be used as a request from a user
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
public class UsernameAndPasswordRequest {

    /**
     * Username of the user
     */
    private String username;

    /**
     * Password of the user
     */
    private String password;

    /**
     * Default constructor of UsernameAndPasswordRequest
     */
    public UsernameAndPasswordRequest() {

    }

    /**
     * Returns username of the user
     *
     * @return Username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets value for the username
     *
     * @param username Username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns password of the user
     *
     * @return Password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets value for the password
     *
     * @param password Password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
