package com.example.assignment3.user.service;

import com.example.assignment3.user.dto.UserDTO;
import com.example.assignment3.user.dto.UserListDTO;
import com.example.assignment3.user.dto.UserMinimalDTO;
import com.example.assignment3.user.mapper.UserMapper;
import com.example.assignment3.user.model.ERole;
import com.example.assignment3.user.model.Role;
import com.example.assignment3.user.model.User;
import com.example.assignment3.user.repository.RoleRepository;
import com.example.assignment3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public User findDoctorByName(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found: " + name));
    }

    public UserDTO create(UserDTO user) {
        return userMapper.fromUser(userRepository.save(
                userMapper.toUser(user)
        ));
    }

    public UserDTO edit(UserDTO user){
        Set<Role> roles = new HashSet<>();
        User actUser = findById(user.getId());
        actUser.setUsername(user.getUsername());
        actUser.setEmail(user.getEmail());
        actUser.setPassword(user.getPassword());
        roles.add(roleRepository.findByName(ERole.valueOf(user.getRoles())).orElse(null));
        actUser.setRoles(roles);
        return userMapper.fromUser(
                userRepository.save(actUser)
        );
    }

    public void delete(Long id) {
        User actUser = userRepository.findById(id).orElse(null);
        userRepository.delete(actUser);
    }

}
