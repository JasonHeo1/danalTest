package com.example.danal.csv.save.pjt.batch.repository;

import com.example.danal.csv.save.pjt.batch.dto.SaveFailureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveFailureLogRepository extends JpaRepository<SaveFailureLog, Long> {
}
