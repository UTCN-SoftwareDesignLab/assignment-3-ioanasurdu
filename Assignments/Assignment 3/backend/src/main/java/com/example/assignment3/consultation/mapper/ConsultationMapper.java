package com.example.assignment3.consultation.mapper;

import com.example.assignment3.consultation.dto.ConsultationDTO;
import com.example.assignment3.consultation.model.Consultation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    ConsultationDTO toDto(Consultation consultation);
    Consultation fromDto(ConsultationDTO consultation);

}
