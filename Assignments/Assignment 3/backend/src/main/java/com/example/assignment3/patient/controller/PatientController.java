package com.example.assignment3.patient.controller;

import com.example.assignment3.patient.dto.PatientDTO;
import com.example.assignment3.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assignment3.UrlMapping.PATIENT;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient) {
        return patientService.create(patient);
    }

    @PatchMapping
    public PatientDTO edit(@RequestBody PatientDTO patient) {
        return patientService.edit(patient);
    }
}
