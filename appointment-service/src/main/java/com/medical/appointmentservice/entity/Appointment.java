package com.medical.appointmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long patientId;
    private long doctorId;

    @Column(name="appointment_date")
    private LocalDateTime appointmentDate;
    private String reason;
    private String status;
}
