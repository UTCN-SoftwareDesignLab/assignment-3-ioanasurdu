package com.example.assignment3.consultation.model;

import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pcn")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(length = 512, nullable = false)
    private String date;

    @Column(length = 512, nullable = false)
    private String time;

    @Column(length = 512, nullable = false)
    private String type;

}
