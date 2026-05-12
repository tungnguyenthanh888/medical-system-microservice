package com.medical.patientservice.service;


import com.medical.patientservice.dto.request.CreatePatientDTO;
import com.medical.patientservice.dto.response.PatientResponseDTO;

public interface PatientService{
    PatientResponseDTO createPatient(CreatePatientDTO payload);
}
