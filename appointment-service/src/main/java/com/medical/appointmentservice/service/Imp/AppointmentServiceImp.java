package com.medical.appointmentservice.service.Imp;

import com.medical.appointmentservice.dto.request.CreateAppointmentDTO;
import com.medical.appointmentservice.dto.response.AppointmentResponseDTO;
import com.medical.appointmentservice.entity.Appointment;
import com.medical.appointmentservice.exception.ResourceNotFoundException;
import com.medical.appointmentservice.exception.ServiceUnavailableException;
import com.medical.appointmentservice.mapper.AppointmentMapper;
import com.medical.appointmentservice.repository.AppointmentRepository;
import com.medical.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private AppointmentMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    private final String PATIENT_SERVICE_URL = "http://PATIENT-SERVICE/api/v1/patients/";
    private final String DOCTOR_SERVICE_URL = "http://DOCTOR-SERVICE/api/v1/doctors/";

    @Override
    public AppointmentResponseDTO createAppointment(CreateAppointmentDTO payload) {
        if(!checkExisted(PATIENT_SERVICE_URL+payload.getPatientId(), "Benh nhan"))
        {
            throw new ResourceNotFoundException("Benh nhan khong ton tai");
        }

        if(!checkExisted(DOCTOR_SERVICE_URL+payload.getDoctorId(), "Bac si"))
        {
            throw new ResourceNotFoundException("Bac si khong ton tai");
        }

        Appointment appointment = mapper.toEntity(payload);

        return mapper.toResponse(repository.save(appointment));
    }

    private boolean checkExisted(String url, String serviceName)
    {
        try{
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        }
        catch (HttpClientErrorException.NotFound e) {
            return false;
        }
        catch (Exception e) {
            throw new ServiceUnavailableException("He thong "+serviceName+" hien khong ka dung. Vui long thu lai sau.");
        }
    }
}
