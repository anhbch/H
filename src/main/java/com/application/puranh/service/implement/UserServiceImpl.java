package com.application.puranh.service.implement;


import com.application.puranh.model.Role;
import com.application.puranh.model.User;
import com.application.puranh.service.DTO.UserRegistrationDto;
import com.application.puranh.model.MyUserDetails;
import com.application.puranh.repository.UserRepository;
import com.application.puranh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.application.puranh.model.Role.Type.ROLE_USER;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User createNewUser(UserRegistrationDto userRegistrationDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (userNameExist(userRegistrationDto.getUserName())) {
            throw new IllegalArgumentException("username already exists");
        }

        User user = new User();
        user.setUserName(userRegistrationDto.getUserName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setFirstName(userRegistrationDto.getFirstName());
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        user.setPassword(encodedPassword);

        Role role = new Role();
        role.setRole(ROLE_USER);
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("user does not exist"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }

    @Override
    public List<Object> getDifferent (LocalDate date) {
//        int[] result = new int[3];
        LocalDate now = LocalDate.now();
        Period period = Period.between(date, now);
        int years = Math.abs(period.getYears());
        int months = Math.abs(period.getMonths());
        int days = Math.abs(period.getDays());
//        result[0] = years;
//        result[1]= months;
//        result[2] = days;
        List<Object> list = new ArrayList<>();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("years", years);
        result.put("months", months);
        result.put("days", days);
        list.add(result);

        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be found");
        }
        return new MyUserDetails(user);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    private boolean userNameExist(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
