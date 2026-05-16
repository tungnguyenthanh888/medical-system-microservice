package com.medical.insuranceservice.controller;

import com.medical.insuranceservice.dto.InsuranceDTO;
import com.medical.insuranceservice.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurances")
@RequiredArgsConstructor
public class InsuranceController {
    @Autowired
    InsuranceService service;

    @GetMapping
    InsuranceDTO getInsurance(@PathVariable String insuranceId)
    {
        return service.getInsurance(insuranceId);
    }

    @PostMapping
    String createInsurance()
    {
        service.createInsurance();
        return "Created new Insurance.";
    }
}
