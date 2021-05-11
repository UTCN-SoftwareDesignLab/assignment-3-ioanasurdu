package com.example.assignment3.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "icn"),
                @UniqueConstraint(columnNames = "pcn")
        })
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 8, nullable = false)
    private String icn;

    @Column(length = 13, nullable = false)
    private String pcn;

    @Column(length = 512, nullable = false)
    private String dateOfBirth;

    @Column(length = 512, nullable = false)
    private String address;
}
