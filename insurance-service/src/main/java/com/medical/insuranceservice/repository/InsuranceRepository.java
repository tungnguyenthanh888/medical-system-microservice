package com.medical.insuranceservice.repository;

import com.medical.insuranceservice.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByInsuranceId(String insuranceId);
}
