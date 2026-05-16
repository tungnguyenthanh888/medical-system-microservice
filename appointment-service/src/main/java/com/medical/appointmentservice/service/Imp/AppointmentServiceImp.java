package com.medical.appointmentservice.service.Imp;

import com.medical.appointmentservice.dto.request.CreateAppointmentDTO;
import com.medical.appointmentservice.dto.response.AppointmentResponseDTO;
import com.medical.appointmentservice.dto.response.InsuranceDTO;
import com.medical.appointmentservice.entity.Appointment;
import com.medical.appointmentservice.exception.ResourceNotFoundException;
import com.medical.appointmentservice.exception.ServiceUnavailableException;
import com.medical.appointmentservice.mapper.AppointmentMapper;
import com.medical.appointmentservice.repository.AppointmentRepository;
import com.medical.appointmentservice.service.AppointmentService;
import com.medical.appointmentservice.service.InsuranceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private AppointmentMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InsuranceClient insuranceClient;

    private final String PATIENT_SERVICE_URL = "http://PATIENT-SERVICE/api/v1/patients/";
    private final String DOCTOR_SERVICE_URL = "http://DOCTOR-SERVICE/api/v1/doctors/";

    @Override
    public AppointmentResponseDTO createAppointment(CreateAppointmentDTO payload) {
        if(!checkPatientExisted(payload.getPatientId()))
        {
            throw new ResourceNotFoundException("Benh nhan khong ton tai");
        }

        if(!checkDoctorExisted(payload.getDoctorId()))
        {
            throw new ResourceNotFoundException("Bac si khong ton tai");
        }

        Appointment appointment = mapper.toEntity(payload);

        return mapper.toResponse(repository.save(appointment));
    }

    @CircuitBreaker(name = "doctorServiceCB", fallbackMethod = "getDoctorFallback")
    @RateLimiter(name="doctorSearchLimit")
    public boolean checkDoctorExisted(Long doctorId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(DOCTOR_SERVICE_URL + doctorId, Void.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new ServiceUnavailableException("He thong Bac si hien khong kha dung.");
        }
    }

    public boolean getDoctorFallback(Long doctorId, Throwable throwable) {
        throw new ServiceUnavailableException("Hien tai khong the kiem tra thong tin bac si. Vui long thu lai sau. (Chi tiet: " + throwable.getMessage() + ")");
    }

    @Retry(name = "patientRetry", fallbackMethod = "getPatientFallback")
    public boolean checkPatientExisted(Long patientId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(PATIENT_SERVICE_URL + patientId, Void.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new ServiceUnavailableException("He thong Benh nhan hien khong kha dung.");
        }
    }

    public boolean getPatientFallback(Long patientId, Throwable throwable) {
        throw new ServiceUnavailableException("He thong hien tai khong the xac thuc thong tin benh nhan.");
    }

    @TimeLimiter(name="insuranceTimeout")
    public CompletableFuture<InsuranceDTO> checkInsurance(String insuranceId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Đang kiểm tra thông tin bảo hiểm cho bệnh nhân: {}", insuranceId);

            simulateSlowNetwork();

            return insuranceClient.getInsuranceById(insuranceId);
        });
    }

    private void simulateSlowNetwork() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
