package com.medical.appointmentservice.service;

import com.medical.appointmentservice.dto.response.InsuranceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "insurance-service", url="lb://insurance-service")
public interface InsuranceClient {
    @GetMapping("/api/insurances/{insuranceId}")
    InsuranceDTO getInsuranceById(@PathVariable("insuranceId") String insuranceId);
}
