package com.example.assignment3.user;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.user.controller.UserController;
import com.example.assignment3.user.dto.UserDTO;
import com.example.assignment3.user.dto.UserListDTO;
import com.example.assignment3.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.assignment3.TestCreationFactory.*;
import static com.example.assignment3.UrlMapping.*;
import static javax.swing.text.html.parser.DTDConstants.ENTITY;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allUsers() throws Exception {
        List<UserListDTO> userListDTOs = TestCreationFactory.listOf(UserListDTO.class);
        when(userService.allUsersForList()).thenReturn(userListDTOs);

        ResultActions result = mockMvc.perform(get(USER));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userListDTOs));
    }

    @Test
    void create() throws Exception {
        UserDTO user = UserDTO.builder()
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .roles("DOCTOR")
                .build();

        when(userService.create(user)).thenReturn(user);

        ResultActions result = performPostWithRequestBody(USER, user);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(user));
    }

    @Test
    void edit() throws Exception {
        long id = randomLong();
        UserDTO user = UserDTO.builder()
                .id(id)
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .roles("DOCTOR")
                .build();

        when(userService.edit(user)).thenReturn(user);

        ResultActions result = performPostWithRequestBody(USER, user);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(user));
    }

    @Test
    void delete() throws Exception {
        long id = randomLong();
        doNothing().when(userService).delete(id);

        ResultActions result = performDeleteWIthPathVariable(USER + ENTITY, id);
        verify(userService, times(1)).delete(id);

        result.andExpect(status().isOk());

    }
}
