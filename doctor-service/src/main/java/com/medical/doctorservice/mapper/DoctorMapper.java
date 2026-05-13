package com.medical.doctorservice.mapper;

import com.medical.doctorservice.dto.request.CreateDoctorDTO;
import com.medical.doctorservice.dto.response.DoctorResponseDTO;
import com.medical.doctorservice.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public Doctor toEntity(CreateDoctorDTO payload)
    {
        Doctor doctor = new Doctor();
        doctor.setName(payload.getName());
        doctor.setEmail(payload.getEmail());
        doctor.setSpecialization(payload.getSpecialization());
        doctor.setExperienceYears(payload.getExperienceYears());
        doctor.setStatus(true);
        return doctor;
    }

    public DoctorResponseDTO toResponse(Doctor doctor)
    {
        return DoctorResponseDTO.builder()
                .id(doctor.getId())
                .specialization(doctor.getSpecialization())
                .name(doctor.getName())
                .build();
    }
}
