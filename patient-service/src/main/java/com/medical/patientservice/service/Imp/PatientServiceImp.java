package com.medical.patientservice.service.Imp;

import com.medical.patientservice.dto.request.CreatePatientDTO;
import com.medical.patientservice.dto.response.PatientResponseDTO;
import com.medical.patientservice.entity.Patient;
import com.medical.patientservice.mapper.PatientMapper;
import com.medical.patientservice.repository.PatientRepository;
import com.medical.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImp implements PatientService {
    @Autowired
    private PatientRepository repository;

    @Autowired
    private PatientMapper mapper;

    @Override
    public PatientResponseDTO createPatient(CreatePatientDTO payload) {
        Patient newPatient = mapper.toEntity(payload);

        return mapper.toResponse(repository.save(newPatient));
    }
}
