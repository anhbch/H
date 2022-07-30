package com.happlication.h.service;


import com.happlication.h.model.User;
import com.happlication.h.service.DTO.UserRegistrationDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUserName(String userName);

    void delete(Long id);

    User createNewUser(UserRegistrationDto userRegistrationDto);

    User editUser(User user);

    List<Object> getDifferent(LocalDate date);
}
