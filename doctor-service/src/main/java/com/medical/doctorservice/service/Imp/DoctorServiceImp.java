package com.medical.doctorservice.service.Imp;

import com.medical.doctorservice.dto.request.CreateDoctorDTO;
import com.medical.doctorservice.dto.response.DoctorResponseDTO;
import com.medical.doctorservice.entity.Doctor;
import com.medical.doctorservice.mapper.DoctorMapper;
import com.medical.doctorservice.repository.DoctorRepository;
import com.medical.doctorservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImp implements DoctorService {
    @Autowired
    private DoctorRepository repository;

    @Autowired
    private DoctorMapper mapper;

    @Override
    public DoctorResponseDTO createPatient(CreateDoctorDTO payload) {
        Doctor newDoctor = mapper.toEntity(payload);

        return mapper.toResponse(repository.save(newDoctor));
    }

    @Override
    public List<DoctorResponseDTO> listDoctor() {
        List<Doctor> doctors = repository.findAll();

        return doctors
                .stream()
                .map((doctor)->
                        mapper.toResponse(doctor)
                )
                .collect(Collectors.toList());
    }
}
