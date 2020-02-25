package com.github.jonizei.clickergame.applicationuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * This class holds all required information for a user
 *
 * @author Joni Koskinen
 * @version 2020-02-25
 */
@Entity
public class ApplicationUser implements UserDetails {

    /**
     * Id of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    /**
     * Username of the user
     */
    private String username;

    /**
     * Password of the user
     */
    @JsonIgnore
    private String password;

    /**
     * Points of the user
     */
    private int points;

    /**
     * Role of the user
     */
    private ApplicationUserRole role;

    /**
     * Boolean which tells if account is expired
     */
    private boolean isAccountNonExpired;

    /**
     * Boolean which tells if account is locked
     */
    private boolean isAccountNonLocked;

    /**
     * Boolean which tells if credentials has expired
     */
    private boolean isCredentialsNonExpired;

    /**
     * Boolean which tells if account is enabled
     */
    private boolean isEnabled;

    /**
     * Default constructor of ApplicationUser
     */
    public ApplicationUser() {

    }

    /**
     * Constructor of ApplicationUser
     *
     * @param username Username of the user
     * @param password Password of the user
     * @param points Points of the user
     * @param role Role of the user
     * @param isAccountNonExpired Boolean which tells if account is expired
     * @param isAccountNonLocked Boolean which tells if account is locked
     * @param isCredentialsNonExpired Boolean which tells if credentials has expired
     * @param isEnabled Boolean which tells if account is enabled
     */
    public ApplicationUser(String username, String password, int points,
                           ApplicationUserRole role, boolean isAccountNonExpired,
                           boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.points = points;
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    /**
     * Returns id of the user
     *
     * @return Id of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set value for id of the user
     *
     * @param userId Id for the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * Sets value for username of the user
     *
     * @param username Username for the user
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
     * Sets value for password of the user
     *
     * @param password Password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns points of the user
     *
     * @return Points of the user
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets value for points of the user
     *
     * @param points Points for the user
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Returns role of the user
     *
     * @return Role of the user
     */
    public ApplicationUserRole getRole() {
        return this.role;
    }

    /**
     * Sets value for role of the user
     *
     * @param role Role for the user
     */
    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    /**
     * Returns expiration status of the user
     *
     * @return Expiration status of the user
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    /**
     * Returns locked status of the user
     *
     * @return Locked status of the user
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    /**
     * Return credentials expiration status of the user
     *
     * @return Credentials expiration status of the user
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    /**
     * Returns enabled status of the user
     *
     * @return Enabled status of the user
     */
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    /**
     * Returns authorities of the user
     *
     * @return Authorities of the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

}
