package com.application.puranh.service;


import com.application.puranh.model.User;
import com.application.puranh.service.DTO.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUserName(String userName);

    void delete(Long id);

    User createNewUser(UserRegistrationDto userRegistrationDto);

    User editUser(User user);

    List<Object> getDifferent(LocalDate date);

    User getByUserName(String userName);
}
