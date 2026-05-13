package com.medical.appointmentservice.service;


import com.medical.appointmentservice.dto.request.CreateAppointmentDTO;
import com.medical.appointmentservice.dto.response.AppointmentResponseDTO;

public interface AppointmentService {
    AppointmentResponseDTO createAppointment(CreateAppointmentDTO payload);
}
