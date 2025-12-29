package com.spring.jwt.service;

import com.spring.jwt.dto.BaseResponseDto;
import com.spring.jwt.dto.EmployeeFarmerSurveyRequestDTO;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;



public interface EmployeeFarmerSurveyService {

    BaseResponseDTO submitSurvey(EmployeeFarmerSurveyRequestDTO dto, Authentication authentication);

    BaseResponseDto getSurveyById(Long surveyId);




    Page<EmployeeFarmerSurveyRequestDTO> getSurveys(
            String formNumber,
            String farmerName,
            String taluka,
            String district,
            Pageable pageable
    );
}
