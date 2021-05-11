package com.example.assignment3.patient.service;

import com.example.assignment3.patient.dto.PatientDTO;
import com.example.assignment3.patient.mapper.PatientMapper;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
    }

    public Patient findByPcn(String pcn) {
        return patientRepository.findByPcn(pcn)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + pcn));
    }

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient) {
        return patientMapper.toDto(patientRepository.save(
                patientMapper.fromDto(patient)
        ));
    }

    public PatientDTO edit(PatientDTO patient) {
        Patient actPatient = findById(patient.getId());
        actPatient.setName(patient.getName());
        actPatient.setIcn(patient.getIcn());
        actPatient.setPcn(patient.getPcn());
        actPatient.setDateOfBirth(patient.getDateOfBirth());
        actPatient.setAddress(patient.getAddress());
        return patientMapper.toDto(
                patientRepository.save(actPatient)
        );
    }
}
