package com.github.jonizei.clickergame.applicationuser;

import com.github.jonizei.clickergame.game.PlayerDetails;
import com.github.jonizei.clickergame.jwt.UsernameAndPasswordRequest;
import com.github.jonizei.clickergame.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * This class handles all requests that focus on users
 *
 * @author Joni Koskinen
 * @version 2020-02-25
 */
@RestController
@RequestMapping("/api/user")
public class ApplicationUserController {

    /**
     * Repository for ApplicationUsers
     */
    private final ApplicationUserRepository applicationUserRepository;

    /**
     * Password encoder for passwords
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Instance of Utilities class
     */
    private final Utilities utilities;

    /**
     * Constructor of ApplicationUserController
     *
     * @param applicationUserRepository Repository for ApplicationUsers
     * @param passwordEncoder Password encoder for passwords
     * @param utilities Instance of Utilities class
     */
    @Autowired
    public ApplicationUserController(ApplicationUserRepository applicationUserRepository, PasswordEncoder passwordEncoder, Utilities utilities) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilities = utilities;
    }

    /**
     * Handles POST requests to "/api/user/details" url
     *
     * Requires role "ROLE_PLAYER"
     *
     * Retrieves username and points of the user by using authentication
     * Initializes PlayerDetails object and sends it as a response to
     * a client
     *
     * @return ResponseEntity with PlayerDetails and HttpStatus.OK
     */
    @PreAuthorize("hasRole('ROLE_PLAYER')")
    @PostMapping("/details")
    public ResponseEntity<PlayerDetails> getPlayerDetails() {

        ApplicationUser applicationUser = utilities.getCurrentUser();
        PlayerDetails playerDetails = new PlayerDetails(applicationUser.getUsername(), applicationUser.getPoints());

        return new ResponseEntity<>(playerDetails, HttpStatus.OK);
    }

    /**
     * Handles POST requests to "/api/user/register" url
     *
     * Request must have UsernameAndPassword object which
     * holds username and password information
     *
     * Creates new user and saves it to the repository of
     * ApplicationUsers if username and password fills all
     * the requirements
     *
     * Requirements:
     * - Username must not exist in the repository
     * - Username length must be longer than three
     * - Password length must be longer than seven
     *
     * @param request POST request from a client
     * @return ResponseEntity with message and HttpStatus.OK
     */
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
