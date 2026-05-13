package com.medical.doctorservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String specialization;

    @Column(name="experience_years")
    private int experienceYears;

    @Column(unique = true, nullable = true)
    private String email;

    private boolean status;
}
