package com.example.assignment3.consultation;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.consultation.controller.ConsultationController;
import com.example.assignment3.consultation.dto.ConsultationDTO;
import com.example.assignment3.consultation.service.ConsultationService;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.assignment3.TestCreationFactory.randomLong;
import static com.example.assignment3.UrlMapping.CONSULTATION;
import static javax.swing.text.html.parser.DTDConstants.ENTITY;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsultationControllerTest extends BaseControllerTest {

    @InjectMocks
    private ConsultationController consultationController;

    @Mock
    private ConsultationService consultationService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        consultationController = new ConsultationController(consultationService);
        mockMvc = MockMvcBuilders.standaloneSetup(consultationController).build();
    }

    @Test
    void allConsultations() throws Exception {
        List<ConsultationDTO> consultationDTOS = TestCreationFactory.listOf(ConsultationDTO.class);
        when(consultationService.findAll()).thenReturn(consultationDTOS);

        ResultActions result = mockMvc.perform(get(CONSULTATION));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTOS));
    }

    @Test
    void create() throws Exception {
        ConsultationDTO consultationDTO = ConsultationDTO.builder()
                .patient(Patient.builder().build())
                .user(User.builder().build())
                .date("date")
                .time("time")
                .type("type")
                .build();

        when(consultationService.create(consultationDTO)).thenReturn(consultationDTO);

        ResultActions result = performPostWithRequestBody(CONSULTATION, consultationDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }

    @Test
    void edit() throws Exception {
        Long id = randomLong();
        ConsultationDTO consultationDTO = ConsultationDTO.builder()
                .id(id)
                .patient(Patient.builder().build())
                .user(User.builder().build())
                .date("date")
                .time("time")
                .type("type")
                .build();

        when(consultationService.edit(consultationDTO)).thenReturn(consultationDTO);

        ResultActions result = performPostWithRequestBody(CONSULTATION, consultationDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }

    @Test
    void delete() throws Exception {
        long id = randomLong();
        doNothing().when(consultationService).delete(id);

        ResultActions result = performDeleteWIthPathVariable(CONSULTATION + ENTITY, id);
        verify(consultationService, times(1)).delete(id);

        result.andExpect(status().isOk());

    }
}
