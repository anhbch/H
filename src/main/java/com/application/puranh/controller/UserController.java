package com.application.puranh.controller;

import com.application.puranh.model.User;
import com.application.puranh.service.UserService;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(User.class);
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<User> getByUserId(@PathVariable(name = "id") Long id) {
        logger.debug("Request to get user by id");
        Optional<User> user = userService.getUserById(id);
        return ResponseUtil.wrapOrNotFound(user);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getByUserName(@PathVariable(name = "username") String userName) {
        logger.debug("Request to get user by username");
        Optional<User> user = userService.getUserByUserName(userName);
        return ResponseUtil.wrapOrNotFound(user);
    }

    @GetMapping(path = "/day-counter/{id}")
    public ResponseEntity<List<Object>> dayCounter (@PathVariable Long id) {
        logger.debug("Request to get day counter");
        Optional<User> user = userService.getUserById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LocalDate date = user.get().getDate();
        return ResponseEntity.ok(userService.getDifferent(date));
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable(name = "id") Long id) {
        logger.debug("Request to delete an user");
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * {@code PUT  /user/edit} : Updates an existing user.
     *
     * @param user the user to update
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user,
     * or with status {@code 400 (Bad Request)} if the user is not valid,
     * or with status {@code 500 (Internal Server Error)} if the user couldn't be updated.
     */
    @PutMapping(path = "/edit")
    public ResponseEntity<User> updateUser (@Valid @RequestBody User user) {
        logger.debug("Request to edit an user");
        User updated = userService.editUser(user);
        return ResponseEntity.ok().body(updated);
    }
}
