package com.medical.patientservice.controller;

import com.medical.patientservice.dto.request.CreatePatientDTO;
import com.medical.patientservice.dto.response.ApiResponse;
import com.medical.patientservice.dto.response.PatientResponseDTO;
import com.medical.patientservice.service.Imp.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientServiceImp service;

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponseDTO>> createPatient(@RequestBody CreatePatientDTO payload)
    {
        PatientResponseDTO patient = service.createPatient(payload);

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(
                        ApiResponse.<PatientResponseDTO>builder()
                                .data(patient)
                                .message("New patient was added.")
                                .status(HttpStatus.CREATED)
                                .build()
                );
    }
}
