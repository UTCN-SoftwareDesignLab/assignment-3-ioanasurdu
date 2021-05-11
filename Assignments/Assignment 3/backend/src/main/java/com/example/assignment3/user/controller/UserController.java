package com.example.assignment3.user.controller;

import com.example.assignment3.user.dto.UserDTO;
import com.example.assignment3.user.dto.UserListDTO;
import com.example.assignment3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assignment3.UrlMapping.USER;


@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PatchMapping
    public void edit(@RequestBody UserDTO user) {
        userService.edit(user);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}