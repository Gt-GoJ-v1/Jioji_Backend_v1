package com.spring.jwt.service;

import com.spring.jwt.dto.BaseResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FarmerSelfieService {

    BaseResponseDto uploadFarmerSelfie(Long surveyId, MultipartFile file );
    BaseResponseDto getSelfieBySurveyId(Long surveyId);
}
