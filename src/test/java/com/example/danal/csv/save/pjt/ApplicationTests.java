package com.example.danal.csv.save.pjt;

import com.example.danal.csv.save.pjt.batch.dto.PublicData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
class ApplicationTests {

//	private static final String FILE_PATH = "/Users/frankheo/Downloads/danal.csv";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private Job importPublicDataJob;

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${csv.input.path}")
	private String csvInputPath;

	@Test
	void testCsvDataImportJob() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("inputFile", csvInputPath)
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		jobLauncherTestUtils.setJob(importPublicDataJob);
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

		assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

		List<PublicData> savedData = entityManager.createQuery("SELECT p FROM PublicData p", PublicData.class)
				.getResultList();

		assertFalse(savedData.isEmpty());

		PublicData firstRecord = savedData.get(0);
		assertNotNull(firstRecord.getServiceName());
		assertNotNull(firstRecord.getManagementNumber());

	}
}