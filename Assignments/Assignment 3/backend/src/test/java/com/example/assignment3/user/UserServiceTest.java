package com.example.assignment3.user;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.user.dto.UserListDTO;
import com.example.assignment3.user.mapper.UserMapper;
import com.example.assignment3.user.repository.RoleRepository;
import com.example.assignment3.user.repository.UserRepository;
import com.example.assignment3.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;


    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, userMapper, roleRepository);
    }

    @Test
    void findAll() {
        List<UserListDTO> userListDTOs = TestCreationFactory.listOf(UserListDTO.class);
        when(userService.allUsersForList()).thenReturn(userListDTOs);

        List<UserListDTO> all = userService.allUsersForList();

        Assertions.assertEquals(userListDTOs.size(), all.size());

    }
}
