package com.github.jonizei.clickergame.applicationuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class implements UserDetailsService that AuthenticationManager will use
 * for authentication
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@Service
public class ApplicationUserService implements UserDetailsService {

    /**
     * Repository for ApplicationUsers
     */
    private final ApplicationUserRepository applicationUserRepository;

    /**
     * Constructor of ApplicationUserService
     *
     * @param applicationUserRepository Repository for ApplicationUsers
     */
    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * Loads ApplicationUser from ApplicationUserRepository using username
     *
     * @param username Username of a user
     * @return Returns ApplicationUser which contains given username
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

}
