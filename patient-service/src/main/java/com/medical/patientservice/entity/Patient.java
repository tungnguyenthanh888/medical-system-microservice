package com.medical.patientservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="date_of_birth")
    private LocalDateTime dateOfBirth;

    private String gender;

    @Column(name="phone_number")
    private String phoneNumber;

    private String address;

    @Column(name="medical_history")
    private String medicalHistory;
}
