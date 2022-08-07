package com.application.puranh.controller;

import com.application.puranh.model.User;
import com.application.puranh.security.JwtTokenGenerator;
import com.application.puranh.service.DTO.UserRegistrationDto;
import com.application.puranh.service.UserService;
import com.application.puranh.model.LoginRequest;
import com.application.puranh.model.LoginResponse;
import com.application.puranh.model.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/api")
public class ApplicationController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin (@Valid @RequestBody LoginRequest loginRequest) {
        try {
            logger.debug("Request to login");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenGenerator.tokenGenerator((MyUserDetails) authentication.getPrincipal());
            return ResponseEntity.ok().body(new LoginResponse(jwt));
        } catch (BadCredentialsException e) {
            logger.error("Failed to login");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    /**
     * {@code POST  /registration} : create new user.
     *
     * @param userRegistrationDto the userRegistrationDto to create
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the created userRegistrationDto,
     * or with status {@code 400 (Bad Request)} if the userRegistrationDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userRegistrationDto couldn't be created.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping (path = "/registration")
    public ResponseEntity<User> registerNewUser (@Valid @RequestBody UserRegistrationDto userRegistrationDto)
            throws URISyntaxException {
        logger.debug("Request to create new user");
        User registered = userService.createNewUser(userRegistrationDto);
        if (registered == null) {
            return null;
        }
        return ResponseEntity.created(new URI("/api/user/" + registered.getId())).body(registered);
    }
}
