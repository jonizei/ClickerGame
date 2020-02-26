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

/**
 * This class handles all requests that focus on the game
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@RestController
@RequestMapping("/api")
public class GameController {

    /**
     * Class that handles all game logic
     */
    private final GameManager gameManager;

    /**
     * Repository for ApplicationUsers
     */
    private final ApplicationUserRepository applicationUserRepository;

    /**
     * Instance of Utilities class
     */
    private final Utilities utilities;

    /**
     * Constructor of GameController
     *
     * @param gameManager Class that handles all game logic
     * @param applicationUserRepository Repository for ApplicationUsers
     * @param utilities Instance of Utilities class
     */
    @Autowired
    public GameController(GameManager gameManager, ApplicationUserRepository applicationUserRepository, Utilities utilities) {
        this.gameManager = gameManager;
        this.applicationUserRepository = applicationUserRepository;
        this.utilities = utilities;
    }

    /**
     * Handles all POST requests to "/api/click" url
     *
     * Requires authority "player:play"
     *
     * Retrieves ApplicationUser by using authentication
     * and retrieves Reward object from GameManager.click() method.
     * Then it updates ApplictionUser's points and returns Reward to a client
     *
     * @return ResponseEntity with Reward object and HttpStatus.OK
     */
    @PreAuthorize("hasAuthority('player:play')")
    @PostMapping("/click")
    public ResponseEntity<Reward> buttonClick() {

        ApplicationUser applicationUser = utilities.getCurrentUser();

        Reward reward = gameManager.click();
        applicationUser.setPoints(applicationUser.getPoints() - 1 + reward.getPoints());
        applicationUserRepository.save(applicationUser);

        return new ResponseEntity<>(reward, HttpStatus.OK);
    }

    /**
     * Handles all POST requests to "/api/click/reset" url
     *
     * Requires role "ROLE_PLAYER"
     *
     * Retrieves ApplicationUser by using authentication
     * and set ApplicationUser's points to 20.
     * Then return PlayerDetails to a client
     *
     * @return ResponseEntity with PlayerDetails object and HttpStatus.OK
     */
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
