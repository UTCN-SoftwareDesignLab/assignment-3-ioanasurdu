package com.example.assignment3.patient;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.patient.controller.PatientController;
import com.example.assignment3.patient.dto.PatientDTO;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.assignment3.UrlMapping.PATIENT;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allPatients() throws Exception {
        List<PatientDTO> patientDTOS = TestCreationFactory.listOf(Patient.class);
        when(patientService.findAll()).thenReturn(patientDTOS);

        ResultActions result = mockMvc.perform(get(PATIENT));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTOS));
    }

    @Test
    void create() throws Exception {
        PatientDTO patient = PatientDTO.builder()
                .name("Patient1")
                .icn("ICN1")
                .pcn("PCN1")
                .dateOfBirth("Date1")
                .address("Address1")
                .build();

        when(patientService.create(patient)).thenReturn(patient);

        ResultActions result = performPostWithRequestBody(PATIENT, patient);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patient));
    }

    @Test
    void edit() throws Exception {
        PatientDTO patient = PatientDTO.builder()
                .id(1L)
                .name("Patient1")
                .icn("ICN1")
                .pcn("PCN1")
                .dateOfBirth("Date1")
                .address("Address1")
                .build();

        when(patientService.edit(patient)).thenReturn(patient);

        ResultActions result = performPostWithRequestBody(PATIENT, patient);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patient));
    }
}
