package com.example.danal.csv.save.pjt.batch.config;
import com.example.danal.csv.save.pjt.batch.dto.PublicData;
import com.example.danal.csv.save.pjt.batch.service.CustomJpaItemWriter;
import com.example.danal.csv.save.pjt.batch.service.PublicDataProcessor;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.format.DateTimeFormatter;

@Configuration
public class BatchConfiguration {

    private static final String FILE_PATH = "/Users/frankheo/Downloads/danal.csv";

    @Bean
    public FlatFileItemReader<PublicData> reader() {
        FlatFileItemReader<PublicData> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(FILE_PATH));
        reader.setLinesToSkip(1);

        DefaultLineMapper<PublicData> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("number", "serviceName", "serviceId", "localGovernmentCode", "managementNumber",
                "permissionDate", "permissionCancelDate", "businessStatusCode", "businessStatusName",
                "detailedStatusCode", "detailedStatusName", "closureDate", "restStartDate", "restEndDate",
                "reopeningDate", "phoneNumber", "area", "zipCode", "fullAddress", "roadNameAddress",
                "roadNameZipCode", "businessName", "lastModifiedTime", "dataUpdateType", "dataUpdateDate",
                "businessType", "coordinateX", "coordinateY", "sanitationBusinessType", "maleEmployees",
                "femaleEmployees", "surroundingClassification", "gradeClassification", "waterFacilityType",
                "totalEmployees", "headOfficeEmployees", "factoryOfficeEmployees", "factorySalesEmployees",
                "factoryProductionEmployees", "buildingOwnershipType", "guaranteeAmount", "monthlyRent",
                "multiUseFacility", "totalFacilitySize", "traditionalBusinessNumber", "traditionalMainFood",
                "website");

        tokenizer.setStrict(false); // 필드 수가 일치하지 않아도 예외를 발생시키지 않음

        BeanWrapperFieldSetMapper<PublicData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PublicData.class);
        fieldSetMapper.setConversionService(conversionService()); // 커스텀 ConversionService 사용

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        DefaultFormattingConversionService formattingConversionService = new DefaultFormattingConversionService();

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        registrar.registerFormatters(formattingConversionService);

        conversionService.addConverter(new Converter<String, String>() {
            @Override
            public String convert(String source) {
                return source.isEmpty() ? null : source;
            }
        });

        return conversionService;
    }

    @Bean
    public PublicDataProcessor processor() {
        return new PublicDataProcessor();
    }

//    @Bean
//    public JpaItemWriter<PublicData> writer(EntityManagerFactory entityManagerFactory) {
//        JpaItemWriter<PublicData> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManagerFactory);
//        return writer;
//    }

    @Bean
    public CustomJpaItemWriter<PublicData> writer(EntityManagerFactory entityManagerFactory) {
        CustomJpaItemWriter<PublicData> writer = new CustomJpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Job importPublicDataJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importPublicDataJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      FlatFileItemReader<PublicData> reader, PublicDataProcessor processor,
                      JpaItemWriter<PublicData> writer) {
        return new StepBuilder("step", jobRepository)
                .<PublicData, PublicData>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .build();
    }
}


