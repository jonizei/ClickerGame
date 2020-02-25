package com.github.jonizei.clickergame.applicationuser;

import com.github.jonizei.clickergame.game.PlayerDetails;
import com.github.jonizei.clickergame.jwt.UsernameAndPasswordRequest;
import com.github.jonizei.clickergame.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final Utilities utilities;

    @Autowired
    public ApplicationUserController(ApplicationUserRepository applicationUserRepository, PasswordEncoder passwordEncoder, Utilities utilities) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilities = utilities;
    }

    @PreAuthorize("hasRole('ROLE_PLAYER')")
    @PostMapping("/details")
    public ResponseEntity<PlayerDetails> getPlayerDetails() {

        ApplicationUser applicationUser = utilities.getCurrentUser();
        PlayerDetails playerDetails = new PlayerDetails(applicationUser.getUsername(), applicationUser.getPoints());

        return new ResponseEntity<>(playerDetails, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UsernameAndPasswordRequest request) {

        String responseMsg = "Username is already taken";

        if(!applicationUserRepository.findByUsername(request.getUsername()).isPresent()) {

            if(request.getUsername().length() > 3) {

                if(request.getPassword().length() > 7) {
                    ApplicationUser newApplicationUser = new ApplicationUser(
                            request.getUsername(),
                            passwordEncoder.encode(request.getPassword()),
                            20,
                            ApplicationUserRole.PLAYER,
                            true,
                            true,
                            true,
                            true
                    );

                    applicationUserRepository.save(newApplicationUser);

                    responseMsg = "User created successfully";
                }
                else responseMsg = "Password is too short";

            }
            else responseMsg = "Username is too short";

        }

        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

}
