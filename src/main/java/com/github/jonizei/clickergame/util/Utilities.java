package com.github.jonizei.clickergame.util;

import com.github.jonizei.clickergame.applicationuser.ApplicationUser;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * This class contains methods that are used in
 * multiple places
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@Component
public class Utilities {

    /**
     * Repository for ApplicationUsers
     */
    private final ApplicationUserRepository applicationUserRepository;

    /**
     * Constructor of Utilities
     *
     * @param applicationUserRepository Repository for ApplicationUsers
     */
    @Autowired
    public Utilities(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * Checks if current user has authentication
     *
     * @return Authentication if it exists. If not then return null
     */
    public Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return auth;
    }

    /**
     * Tries to find user from the repository by username using authentication
     *
     * Returns ApplicationUser if it exists otherwise throws exception
     *
     * @return ApplicationUser that has been found by username
     */
    public ApplicationUser getCurrentUser() {

        final String usernameToBeFound = getAuthentication() != null ? getAuthentication().getName() : "";
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(usernameToBeFound)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find username: %s", usernameToBeFound)));

        return  applicationUser;
    }

}
