package com.medical.appointmentservice.dto.response;

import java.time.LocalDateTime;

public record InsuranceDTO(long id, String insuranceId, LocalDateTime expiredAt) {
}
