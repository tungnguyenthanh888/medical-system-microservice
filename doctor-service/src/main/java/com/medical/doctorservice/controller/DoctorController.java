package com.medical.doctorservice.controller;

import com.medical.doctorservice.dto.request.CreateDoctorDTO;
import com.medical.doctorservice.dto.response.ApiResponse;
import com.medical.doctorservice.dto.response.DoctorResponseDTO;
import com.medical.doctorservice.service.Imp.DoctorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Autowired
    private DoctorServiceImp doctorServiceImp;

    @GetMapping
    private ResponseEntity<ApiResponse<List<DoctorResponseDTO>>> listDoctors()
    {
        List<DoctorResponseDTO> doctors = doctorServiceImp.listDoctor();
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(
                        ApiResponse.<List<DoctorResponseDTO>>builder()
                                .message("Get list of doctor.")
                                .status(HttpStatus.OK)
                                .data(doctors)
                                .build()
                );
    }

    @PostMapping
    private ResponseEntity<ApiResponse<DoctorResponseDTO>> createDoctor(@RequestBody CreateDoctorDTO payload)
    {
        DoctorResponseDTO result = doctorServiceImp.createPatient(payload);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(
                        ApiResponse.<DoctorResponseDTO>builder()
                                .message("New doctor was added.")
                                .status(HttpStatus.CREATED)
                                .data(result)
                                .build()
                );
    }
}
