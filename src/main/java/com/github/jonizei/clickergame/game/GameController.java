package com.github.jonizei.clickergame.game;

import com.github.jonizei.clickergame.applicationuser.ApplicationUser;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserRepository;
import com.github.jonizei.clickergame.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameManager gameManager;
    private final ApplicationUserRepository applicationUserRepository;
    private final Utilities utilities;

    @Autowired
    public GameController(GameManager gameManager, ApplicationUserRepository applicationUserRepository, Utilities utilities) {
        this.gameManager = gameManager;
        this.applicationUserRepository = applicationUserRepository;
        this.utilities = utilities;
    }

    @PreAuthorize("hasRole('ROLE_PLAYER')")
    @PostMapping("/click")
    public ResponseEntity<Reward> buttonClick() {

        ApplicationUser applicationUser = utilities.getCurrentUser();

        Reward reward = gameManager.click();
        applicationUser.setPoints(applicationUser.getPoints() + reward.getPoints());
        applicationUserRepository.save(applicationUser);

        return new ResponseEntity<>(reward, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PLAYER')")
    @PostMapping("/click/reset")
    public ResponseEntity<PlayerDetails> resetPoints() {

        ApplicationUser applicationUser = utilities.getCurrentUser();

        PlayerDetails playerDetails = new PlayerDetails(applicationUser.getUsername(), 20);

        applicationUser.setPoints(20);
        applicationUserRepository.save(applicationUser);

        return new ResponseEntity<>(playerDetails, HttpStatus.OK);
    }

}
