package com.spring.jwt.service.impl;

import com.spring.jwt.dto.BaseResponseDto;
import com.spring.jwt.entity.EmployeeFarmerSurvey;
import com.spring.jwt.entity.FarmerSelfie;
import com.spring.jwt.exception.ResourceAlreadyExistsException;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.repository.EmployeeFarmerSurveyRepository;
import com.spring.jwt.repository.FarmerSelfieRepository;
import com.spring.jwt.service.FarmerSelfieService;
import com.spring.jwt.service.FileStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerSelfieService {

    private  final EmployeeFarmerSurveyRepository surveyRepository;
    private final FarmerSelfieRepository farmerSelfieRepository;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional
    public BaseResponseDto uploadFarmerSelfie(Long surveyId, MultipartFile file){

        EmployeeFarmerSurvey survey=surveyRepository.findById(surveyId)
                .orElseThrow(()->new ResourceNotFoundException("Survey Not Found with id:" + surveyId));

        if (farmerSelfieRepository.existsBySurvey_SurveyId(surveyId)){
            throw new ResourceAlreadyExistsException("Survey Already Exists for this Survey");
        }
        //upload img to store and get url
        String imgUrl= fileStorageService.store(file);

        FarmerSelfie selfie=new FarmerSelfie();
        selfie.setSurvey(survey);
        selfie.setImageUrl(imgUrl);

        farmerSelfieRepository.save(selfie);

        return new BaseResponseDto(
                "200",
                "Selfie Uploaded Successfully",
                selfie.getSelfieId()
        );

    }
    @Override
    @Transactional
    public BaseResponseDto getSelfieBySurveyId(Long surveyId){
        FarmerSelfie selfie=farmerSelfieRepository.findBySurvey_SurveyId(surveyId)
                .orElseThrow(()->new ResourceNotFoundException("Selfie Not Found with id:" + surveyId));
        return new BaseResponseDto(
                "200",
                "Farmer Selfie Fetched Successfully",
                selfie.getImageUrl()
        );
    }



}
