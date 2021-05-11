package com.example.assignment3.patient.mapper;

import com.example.assignment3.patient.dto.PatientDTO;
import com.example.assignment3.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDto(Patient patient);

    Patient fromDto(PatientDTO patient);
}
