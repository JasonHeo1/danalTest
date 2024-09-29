1. 공공데이터 저장 테이블
   ##데이터 무결성 외래 키 관계 설정:
PUBLIC_DATA 테이블에 스프링 배치 실행 정보를 연결하는 컬럼을 추가하고, 이를 BATCH_JOB_EXECUTION 테이블과 연결합니다.
``` sql
-- 공공데이터 저장 테이블
CREATE TABLE PUBLIC_DATA (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NUMBER VARCHAR(200),
    SERVICE_NAME VARCHAR(200),
    SERVICE_ID VARCHAR(200),
    LOCAL_GOVERNMENT_CODE VARCHAR(200),
    MANAGEMENT_NUMBER VARCHAR(200),
    PERMISSION_DATE VARCHAR(200),
    PERMISSION_CANCEL_DATE VARCHAR(200),
    BUSINESS_STATUS_CODE VARCHAR(200),
    BUSINESS_STATUS_NAME VARCHAR(200),
    DETAILED_STATUS_CODE VARCHAR(200),
    DETAILED_STATUS_NAME VARCHAR(200),
    CLOSURE_DATE VARCHAR(200),
    REST_START_DATE VARCHAR(200),
    REST_END_DATE VARCHAR(200),
    REOPENING_DATE VARCHAR(200),
    PHONE_NUMBER VARCHAR(200),
    AREA VARCHAR(200),
    ZIP_CODE VARCHAR(200),
    FULL_ADDRESS VARCHAR(200),
    ROAD_NAME_ADDRESS VARCHAR(200),
    ROAD_NAME_ZIP_CODE VARCHAR(200),
    BUSINESS_NAME VARCHAR(200),
    LAST_MODIFIED_TIME VARCHAR(200),
    DATA_UPDATE_TYPE VARCHAR(200),
    DATA_UPDATE_DATE VARCHAR(200),
    BUSINESS_TYPE VARCHAR(200),
    COORDINATE_X VARCHAR(200),
    COORDINATE_Y VARCHAR(200),
    SANITATION_BUSINESS_TYPE VARCHAR(200),
    MALE_EMPLOYEES VARCHAR(200),
    FEMALE_EMPLOYEES VARCHAR(200),
    SURROUNDING_CLASSIFICATION VARCHAR(200),
    GRADE_CLASSIFICATION VARCHAR(200),
    WATER_FACILITY_TYPE VARCHAR(200),
    TOTAL_EMPLOYEES VARCHAR(200),
    HEAD_OFFICE_EMPLOYEES VARCHAR(200),
    FACTORY_OFFICE_EMPLOYEES VARCHAR(200),
    FACTORY_SALES_EMPLOYEES VARCHAR(200),
    FACTORY_PRODUCTION_EMPLOYEES VARCHAR(200),
    BUILDING_OWNERSHIP_TYPE VARCHAR(200),
    GUARANTEE_AMOUNT VARCHAR(200),
    MONTHLY_RENT VARCHAR(200),
    MULTI_USE_FACILITY VARCHAR(200),
    TOTAL_FACILITY_SIZE VARCHAR(200),
    TRADITIONAL_BUSINESS_NUMBER VARCHAR(200),
    TRADITIONAL_MAIN_FOOD VARCHAR(200),
    WEBSITE VARCHAR(200),
    JOB_EXECUTION_ID BIGINT,
    CONSTRAINT UK_PUBLIC_DATA UNIQUE (MANAGEMENT_NUMBER),
    CONSTRAINT FK_JOB_EXECUTION FOREIGN KEY (JOB_EXECUTION_ID) REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);

-- 인덱스 생성 (선택사항이지만 성능 향상을 위해 권장)
CREATE INDEX IDX_PUBLIC_DATA_JOB_EXECUTION_ID ON PUBLIC_DATA(JOB_EXECUTION_ID);
CREATE INDEX IDX_PUBLIC_DATA_MANAGEMENT_NUMBER ON PUBLIC_DATA(MANAGEMENT_NUMBER);


-- 파일 저장 실패 추적용 로그 저장 테이블
CREATE TABLE save_failure_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255),
    error_message TEXT,
    failed_data TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
##2.데이터 저장 실패시 logback을 통한 데이터 저장, DB를 통한 데이터 저장하여 추적을 정확하게 할 수 있음.

##3.Application 실행 시 java/com/example/danal/csv/save/pjt/batch/config/BatchConfiguration.java 클래스의 csv 파일 경로를 설정필요 함. private static final String FILE_PATH = "/Users/frankheo/Downloads/danal.csv";

##4.테스트 케이스 (Junit) 실행 시 별도의 test db 구성 권장함. ddl은 위와 같이 동일하게 실행, 생성하면 됨.





