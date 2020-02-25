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

@Component
public class Utilities {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public Utilities(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return auth;
    }

    public ApplicationUser getCurrentUser() {

        final String usernameToBeFound = getAuthentication().getName();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(usernameToBeFound)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find username: %s", usernameToBeFound)));

        return  applicationUser;
    }

}
