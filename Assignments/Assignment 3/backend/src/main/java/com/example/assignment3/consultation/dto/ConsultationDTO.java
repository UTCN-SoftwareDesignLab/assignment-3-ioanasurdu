package com.example.assignment3.consultation.dto;

import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private Patient patient;
    private User user;
    private String date;
    private String time;
    private String type;
}
