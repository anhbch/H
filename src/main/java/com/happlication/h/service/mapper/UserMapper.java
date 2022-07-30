package com.happlication.h.service.mapper;

import com.happlication.h.model.User;
import com.happlication.h.service.DTO.UserRegistrationDto;
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