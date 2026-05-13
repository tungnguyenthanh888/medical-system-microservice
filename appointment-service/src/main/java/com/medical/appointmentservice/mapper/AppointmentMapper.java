package com.medical.appointmentservice.mapper;


import com.medical.appointmentservice.dto.request.CreateAppointmentDTO;
import com.medical.appointmentservice.dto.response.AppointmentResponseDTO;
import com.medical.appointmentservice.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment toEntity(CreateAppointmentDTO payload)
    {
        Appointment appointment = new Appointment();
        appointment.setStatus("PENDING");
        appointment.setReason(payload.getReason());
        appointment.setDoctorId(payload.getDoctorId());
        appointment.setPatientId(payload.getPatientId());
        return appointment;
    }

    public AppointmentResponseDTO toResponse(Appointment appointment)
    {
        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .status(appointment.getStatus())
                .doctorId(appointment.getDoctorId())
                .patientId(appointment.getPatientId())
                .reason(appointment.getReason())
                .build();
    }
}
