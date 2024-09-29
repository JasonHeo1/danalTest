package com.example.danal.csv.save.pjt.batch.service;
import com.example.danal.csv.save.pjt.batch.dto.PublicData;
import com.example.danal.csv.save.pjt.batch.dto.SaveFailureLog;
import com.example.danal.csv.save.pjt.batch.repository.PublicDataRepository;
import com.example.danal.csv.save.pjt.batch.repository.SaveFailureLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicDataProcessor implements ItemProcessor<PublicData, PublicData> {

    @Autowired
    private PublicDataRepository publicDataRepository;

    @Autowired
    private SaveFailureLogRepository saveFailureLogRepository;

    private static final Logger logger = LoggerFactory.getLogger(PublicDataProcessor.class);

    @Override
    public PublicData process(PublicData item) throws Exception {
        try {
            // 중복 체크
            if (publicDataRepository.existsByManagementNumber(item.getManagementNumber())) {
                return null;
            }

            return item;
        } catch (Exception e) {
            // 실패 로그 저장
            SaveFailureLog failureLog = new SaveFailureLog();
            failureLog.setFileName("danal.csv");
            failureLog.setErrorMessage(e.getMessage());
            failureLog.setFailedData(item.toString());
            saveFailureLogRepository.save(failureLog);

            // Error log 파일에 저장
            logger.error("Data processing failed for item: {}. Error: {}", item.getManagementNumber(), e.getMessage(), e);
            throw e;
        }
    }
}
