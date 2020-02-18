package com.github.jonizei.clickergame.game;

import com.github.jonizei.clickergame.applicationuser.ApplicationUser;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameManager gameManager;
    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public GameController(GameManager gameManager, ApplicationUserRepository applicationUserRepository) {
        this.gameManager = gameManager;
        this.applicationUserRepository = applicationUserRepository;
    }

    @PreAuthorize("hasRole('ROLE_PLAYER')")
    @PostMapping("/click/{playerId}")
    public ResponseEntity<Integer> buttonClick(@PathVariable int playerId) {
        ApplicationUser applicationUser = applicationUserRepository.findById(playerId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find player with id %d", playerId)));

        int reward = gameManager.click();
        applicationUser.setPoints(applicationUser.getPoints() + reward - 1);
        applicationUserRepository.save(applicationUser);

        return new ResponseEntity<>(reward, HttpStatus.OK);
    }

}
