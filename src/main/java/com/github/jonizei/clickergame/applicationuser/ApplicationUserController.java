package com.github.jonizei.clickergame.applicationuser;

import com.github.jonizei.clickergame.game.PlayerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserController(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @PostMapping("/details")
    public ResponseEntity<PlayerDetails> getPlayerDetails() {

        String username = "";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            username = auth.getName();
        }
        else return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        final String usernameToBeFound = username;
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(usernameToBeFound)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to username: %s", usernameToBeFound)));

        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setId(applicationUser.getUserId());
        playerDetails.setUsername(applicationUser.getUsername());
        playerDetails.setPoints(applicationUser.getPoints());

        return new ResponseEntity<>(playerDetails, HttpStatus.OK);
    }

}
