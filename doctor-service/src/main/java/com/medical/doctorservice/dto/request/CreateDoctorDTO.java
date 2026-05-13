package com.medical.doctorservice.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorDTO {
    private String name;
    private String specialization;
    private int experienceYears;
    private String email;
    private boolean status;
}
