package com.example.assignment3.user.mapper;

import com.example.assignment3.user.dto.UserDTO;
import com.example.assignment3.user.dto.UserListDTO;
import com.example.assignment3.user.dto.UserMinimalDTO;
import com.example.assignment3.user.model.User;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "name", source = "user.username"),
            @Mapping(target = "roles", ignore = true)
    })
    UserListDTO userListDtoFromUser(User user);

    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {
        userListDTO.setRoles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }

    @Mappings({
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "password", source = "user.password"),
            @Mapping(target = "roles", ignore = true)
    })
    User toUser(UserDTO user);

    @Mappings({
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "password", source = "user.password"),
            @Mapping(target = "roles", ignore = true)
    })
    UserDTO fromUser(User user);

}
