package com.example.danal.csv.save.pjt.batch.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "save_failure_log")
public class SaveFailureLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "failed_data", columnDefinition = "TEXT")
    private String failedData;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
