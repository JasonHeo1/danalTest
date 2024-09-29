package com.example.danal.csv.save.pjt.batch.repository;

import com.example.danal.csv.save.pjt.batch.dto.PublicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicDataRepository extends JpaRepository<PublicData, Long> {
    boolean existsByManagementNumber(String managementNumber);
}
