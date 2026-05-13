package com.medical.appointmentservice.controller;

import com.medical.appointmentservice.dto.request.CreateAppointmentDTO;
import com.medical.appointmentservice.dto.response.ApiResponse;
import com.medical.appointmentservice.dto.response.AppointmentResponseDTO;
import com.medical.appointmentservice.service.Imp.AppointmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentServiceImp service;

    @PostMapping
    private ResponseEntity<ApiResponse<AppointmentResponseDTO>> createDoctor(@RequestBody CreateAppointmentDTO payload)
    {
        AppointmentResponseDTO result = service.createAppointment(payload);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(
                        ApiResponse.<AppointmentResponseDTO>builder()
                                .message("New appointment was added.")
                                .status(HttpStatus.CREATED)
                                .data(result)
                                .build()
                );
    }
}
