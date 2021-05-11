package com.example.assignment3.consultation.repository;

import com.example.assignment3.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
