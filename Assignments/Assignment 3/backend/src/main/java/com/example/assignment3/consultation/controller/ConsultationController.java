package com.example.assignment3.consultation.controller;

import com.example.assignment3.consultation.dto.ConsultationDTO;
import com.example.assignment3.consultation.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assignment3.UrlMapping.CONSULTATION;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> allConsultations() {
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO create(@RequestBody ConsultationDTO consultation) {
        return consultationService.create(consultation);
    }

    @PatchMapping
    public ConsultationDTO edit(@RequestBody ConsultationDTO consultation) {
        return consultationService.edit(consultation);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        consultationService.delete(id);
    }

}
