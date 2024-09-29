package com.example.danal.csv.save.pjt.batch.service;

import com.example.danal.csv.save.pjt.batch.dto.PublicData;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;

import jakarta.persistence.EntityManager;

public class CustomJpaItemWriter<T> extends JpaItemWriter<T> {

    private StepExecution stepExecution;

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    protected void doWrite(EntityManager entityManager, Chunk<? extends T> items) {
        if (items.isEmpty()) {
            return;
        }

        Long jobExecutionId = stepExecution.getJobExecutionId();

        for (T item : items) {
            if (item instanceof PublicData) {
                ((PublicData) item).setJobExecutionId(jobExecutionId);
            }
            entityManager.merge(item);
        }
    }
}