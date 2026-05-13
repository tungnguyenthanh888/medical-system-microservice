package com.medical.doctorservice.service;


import com.medical.doctorservice.dto.request.CreateDoctorDTO;
import com.medical.doctorservice.dto.response.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {
    DoctorResponseDTO createPatient(CreateDoctorDTO payload);
    List<DoctorResponseDTO> listDoctor();
}
