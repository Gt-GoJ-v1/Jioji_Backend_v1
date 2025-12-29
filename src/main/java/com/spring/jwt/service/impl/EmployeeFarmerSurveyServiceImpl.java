package com.spring.jwt.service.impl;

import com.spring.jwt.dto.BaseResponseDto;
import com.spring.jwt.dto.EmployeeFarmerSurveyRequestDTO;
import com.spring.jwt.entity.EmployeeFarmerSurvey;
import com.spring.jwt.exception.UserAlreadyExistException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.mapper.EmployeeFarmerSurveyMapper;
import com.spring.jwt.repository.EmployeeFarmerSurveyRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.EmployeeFarmerSurveyService;
import com.spring.jwt.utils.BaseResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeFarmerSurveyServiceImpl implements EmployeeFarmerSurveyService {

    private final EmployeeFarmerSurveyRepository employeeFarmerSurveyRepository;
    private final UserRepository userRepository;
    private final EmployeeFarmerSurveyMapper surveyMapper;

    @Override
    @Transactional
    public BaseResponseDTO submitSurvey(EmployeeFarmerSurveyRequestDTO dto, Authentication authentication){

        //Get Logged in employee email from jwt
//        String email = authentication.getName();
//        log.info("Employee [{}] started farmer survey submission", email);
//
//        //fetch employee
//        User employee=userRepository.findByEmail(email);
//        if (employee==null){
//            log.error("Employee [{}] not found", email);
//            throw new UserNotFoundExceptions("Employee ["+email+"] not found");
//        }
        if (employeeFarmerSurveyRepository.existsByFormNumber(dto.getFormNumber())){
            throw new UserAlreadyExistException("Form number ["+dto.getFormNumber()+"] is already exist");
        }
        EmployeeFarmerSurvey survey=surveyMapper.toEntity(dto,null);

        employeeFarmerSurveyRepository.save(survey);
       // log.info("Survey saved successfully. FormNumber={}, EmployeeId={}",dto.getFormNumber(),employee.getId());


        return new BaseResponseDTO(
                "200",
                "Farmer Survey Submitted Successfully",null
                //employee.getId()
        );
    }

    @Override
    @Transactional
    public BaseResponseDto getSurveyById(Long surveyId) {
       EmployeeFarmerSurvey survey= employeeFarmerSurveyRepository.findById(surveyId)
                .orElseThrow(() -> new UserNotFoundExceptions("Survey not found with id ["+surveyId+"]"));
        return new BaseResponseDto(
                "200",
                "Survey fetched successfully",
                surveyMapper.toDto(survey)
        );
    }
    @Override
    public Page<EmployeeFarmerSurveyRequestDTO> getSurveys(
            String formNumber,
            String farmerName,
            String taluka,
            String district,
            Pageable pageable
    ) {

        Page<EmployeeFarmerSurvey> surveys =
                employeeFarmerSurveyRepository.findAll(pageable);

        return new PageImpl<>(
                surveys.stream()
                        .filter(survey -> formNumber == null || survey.getFormNumber().equalsIgnoreCase(formNumber))
                        .filter(survey -> farmerName == null || survey.getFarmerName().equalsIgnoreCase(farmerName))
                        .filter(survey -> taluka == null || survey.getTaluka().equalsIgnoreCase(taluka))
                        .filter(survey -> district == null || survey.getDistrict().equalsIgnoreCase(district))
                        .map(surveyMapper::toDto)
                        .toList(),
                pageable,
                surveys.getTotalElements()
        );
    }
}
