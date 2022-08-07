package com.application.puranh.service.mapper;

import com.application.puranh.model.User;
import com.application.puranh.service.DTO.UserRegistrationDto;
import org.mapstruct.Mapper;


@Mapper (componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserRegistrationDto, User> {

    default User fromId (Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}