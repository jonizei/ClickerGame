package com.github.jonizei.clickergame.applicationuser;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.github.jonizei.clickergame.applicationuser.ApplicationUserPermission.PLAYER_PLAY;

/**
 * This class holds all roles of the application
 *
 * @author Joni Koskinen
 * @version 2020-02-25
 */
public enum ApplicationUserRole {

    /**
     * Role PLAYER and its permissions
     */
    PLAYER(Sets.newHashSet(PLAYER_PLAY));

    /**
     * Set of permissions
     */
    private final Set<ApplicationUserPermission> permissions;

    /**
     * Constructor of ApplicationUserRole
     *
     * @param permissions Set of permissions
     */
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Returns role's permissions in a Set
     *
     * @return Set of permissions
     * @return Set of permissions
     */
    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    /**
     * Returns role's permissions as Set of SimpleGrantedAuthorities
     *
     * Converts a Set of ApplicationUserPermission to a Set of SimpleGrantedAuthorities
     * Adds role to the Set of SimpleGrantedAuthorities
     *
     * @return Set of SimpleGrantedAuthorities
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
