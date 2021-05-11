package com.example.assignment3.patient;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.patient.dto.PatientDTO;
import com.example.assignment3.patient.repository.PatientRepository;
import com.example.assignment3.patient.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.when;

public class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void findAll() {
        List<PatientDTO> patientDTOS = TestCreationFactory.listOf(PatientDTO.class);
        when(patientService.findAll()).thenReturn(patientDTOS);

        List<PatientDTO> all = patientService.findAll();

        Assertions.assertEquals(patientDTOS.size(), all.size());
    }
}
