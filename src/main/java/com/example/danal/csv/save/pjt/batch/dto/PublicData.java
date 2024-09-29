package com.example.danal.csv.save.pjt.batch.dto;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PUBLIC_DATA")
public class PublicData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = "SERVICE_ID")
    private String serviceId;

    @Column(name = "LOCAL_GOVERNMENT_CODE")
    private String localGovernmentCode;

    @Column(name = "MANAGEMENT_NUMBER", unique = true)
    private String managementNumber;

    @Column(name = "PERMISSION_DATE")
    private String permissionDate;

    @Column(name = "PERMISSION_CANCEL_DATE")
    private String permissionCancelDate;

    @Column(name = "BUSINESS_STATUS_CODE")
    private String businessStatusCode;

    @Column(name = "BUSINESS_STATUS_NAME")
    private String businessStatusName;

    @Column(name = "DETAILED_STATUS_CODE")
    private String detailedStatusCode;

    @Column(name = "DETAILED_STATUS_NAME")
    private String detailedStatusName;

    @Column(name = "CLOSURE_DATE")
    private String closureDate;

    @Column(name = "REST_START_DATE")
    private String restStartDate;

    @Column(name = "REST_END_DATE")
    private String restEndDate;

    @Column(name = "REOPENING_DATE")
    private String reopeningDate;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "AREA")
    private String area;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "FULL_ADDRESS")
    private String fullAddress;

    @Column(name = "ROAD_NAME_ADDRESS")
    private String roadNameAddress;

    @Column(name = "ROAD_NAME_ZIP_CODE")
    private String roadNameZipCode;

    @Column(name = "BUSINESS_NAME")
    private String businessName;

    @Column(name = "LAST_MODIFIED_TIME")
    private String lastModifiedTime;

    @Column(name = "DATA_UPDATE_TYPE")
    private String dataUpdateType;

    @Column(name = "DATA_UPDATE_DATE")
    private String dataUpdateDate;

    @Column(name = "BUSINESS_TYPE")
    private String businessType;

    @Column(name = "COORDINATE_X")
    private String coordinateX;

    @Column(name = "COORDINATE_Y")
    private String coordinateY;

    @Column(name = "SANITATION_BUSINESS_TYPE")
    private String sanitationBusinessType;

    @Column(name = "MALE_EMPLOYEES")
    private String maleEmployees;

    @Column(name = "FEMALE_EMPLOYEES")
    private String femaleEmployees;

    @Column(name = "SURROUNDING_CLASSIFICATION")
    private String surroundingClassification;

    @Column(name = "GRADE_CLASSIFICATION")
    private String gradeClassification;

    @Column(name = "WATER_FACILITY_TYPE")
    private String waterFacilityType;

    @Column(name = "TOTAL_EMPLOYEES")
    private String totalEmployees;

    @Column(name = "HEAD_OFFICE_EMPLOYEES")
    private String headOfficeEmployees;

    @Column(name = "FACTORY_OFFICE_EMPLOYEES")
    private String factoryOfficeEmployees;

    @Column(name = "FACTORY_SALES_EMPLOYEES")
    private String factorySalesEmployees;

    @Column(name = "FACTORY_PRODUCTION_EMPLOYEES")
    private String factoryProductionEmployees;

    @Column(name = "BUILDING_OWNERSHIP_TYPE")
    private String buildingOwnershipType;

    @Column(name = "GUARANTEE_AMOUNT")
    private String guaranteeAmount;

    @Column(name = "MONTHLY_RENT")
    private String monthlyRent;

    @Column(name = "MULTI_USE_FACILITY")
    private String multiUseFacility;

    @Column(name = "TOTAL_FACILITY_SIZE")
    private String totalFacilitySize;

    @Column(name = "TRADITIONAL_BUSINESS_NUMBER")
    private String traditionalBusinessNumber;

    @Column(name = "TRADITIONAL_MAIN_FOOD")
    private String traditionalMainFood;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "JOB_EXECUTION_ID")
    private Long jobExecutionId;

}