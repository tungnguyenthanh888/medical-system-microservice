package com.medical.insuranceservice.dto;

import java.time.LocalDateTime;

public record InsuranceDTO(long id, String insuranceId, LocalDateTime expiredAt) {
}
