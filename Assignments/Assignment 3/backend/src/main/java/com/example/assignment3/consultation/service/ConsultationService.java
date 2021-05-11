package com.example.assignment3.consultation.service;

import com.example.assignment3.consultation.dto.ConsultationDTO;
import com.example.assignment3.consultation.mapper.ConsultationMapper;
import com.example.assignment3.consultation.model.Consultation;
import com.example.assignment3.consultation.repository.ConsultationRepository;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.patient.service.PatientService;
import com.example.assignment3.user.model.User;
import com.example.assignment3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final PatientService patientService;
    private final UserService userService;

    private Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }

    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ConsultationDTO create(ConsultationDTO consultation) {
        Patient patient = patientService.findByPcn(consultation.getPatient().getPcn());
        User user = userService.findDoctorByName(consultation.getUser().getUsername());
        Consultation actConsultation = findById(consultation.getId());

        actConsultation.setPatient(patient);
        actConsultation.setUser(user);
        actConsultation.setDate(consultation.getDate());
        actConsultation.setTime(consultation.getTime());
        actConsultation.setType(consultation.getType());

        return consultationMapper.toDto(consultationRepository.save(
                consultationMapper.fromDto(consultation)
        ));
    }

    public ConsultationDTO edit(ConsultationDTO consultation) {
        Consultation actConsultation = findById(consultation.getId());
        actConsultation.setDate(consultation.getDate());
        actConsultation.setTime(consultation.getTime());
        actConsultation.setType(consultation.getType());

        return consultationMapper.toDto(
                consultationRepository.save(actConsultation)
        );
    }

    public void delete(Long id) {
        Consultation consultation = consultationRepository.findById(id).orElse(null);
        consultationRepository.delete(consultation);
    }
}
