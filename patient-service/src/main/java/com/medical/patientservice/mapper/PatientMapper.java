package com.medical.patientservice.mapper;

import com.medical.patientservice.dto.request.CreatePatientDTO;
import com.medical.patientservice.dto.response.PatientResponseDTO;
import com.medical.patientservice.entity.Patient;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient toEntity(CreatePatientDTO payload)
    {
        Patient patient = new Patient();
        patient.setFullName(payload.getFullName());
        patient.setAddress(payload.getAddress());
        patient.setMedicalHistory(payload.getMedicalHistory());
        return patient;
    }

    public PatientResponseDTO toResponse(Patient patient)
    {
        return PatientResponseDTO.builder()
                .id(patient.getId())
                .address(patient.getAddress())
                .fullName(patient.getFullName())
                .medicalHistory(patient.getMedicalHistory())
                .address(patient.getAddress())
                .build();
    }
}
