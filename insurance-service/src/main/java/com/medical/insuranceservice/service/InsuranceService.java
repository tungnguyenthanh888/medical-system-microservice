package com.medical.insuranceservice.service;

import com.medical.insuranceservice.dto.InsuranceDTO;
import com.medical.insuranceservice.entity.Insurance;
import com.medical.insuranceservice.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    @Autowired
    InsuranceRepository repo;

    public InsuranceDTO getInsurance(String insuranceId)
    {
        Insurance insurance = repo.findByInsuranceId(insuranceId).orElseThrow(() -> new NoSuchElementException("Not found insurance."));
        return new InsuranceDTO(insurance.getId(), insurance.getInsuranceId(), insurance.getExpiredAt());
    }

    public void createInsurance()
    {
        Insurance insurance = new Insurance();
        insurance.setInsuranceId(UUID.randomUUID().toString());
        insurance.setExpiredAt(LocalDateTime.now().plusYears(2));
        repo.save(insurance);
    }
}
