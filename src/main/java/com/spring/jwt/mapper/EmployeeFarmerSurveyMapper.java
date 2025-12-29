package com.spring.jwt.mapper;

import com.spring.jwt.dto.EmployeeFarmerSurveyRequestDTO;
import com.spring.jwt.entity.EmployeeFarmerSurvey;
import com.spring.jwt.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFarmerSurveyMapper {

    public EmployeeFarmerSurvey toEntity(EmployeeFarmerSurveyRequestDTO dto, User employee){
        EmployeeFarmerSurvey survey = new EmployeeFarmerSurvey();
        survey.setFormNumber( dto.getFormNumber());
        survey.setFarmerName(dto.getFarmerName());
        survey.setFarmerMobile(dto.getFarmerMobile());
        survey.setLandArea(dto.getLandArea());
        survey.setAddress(dto.getAddress());
        survey.setTaluka(dto.getTaluka());
        survey.setDistrict(dto.getDistrict());

        survey.setSampleCollected(dto.getSampleCollected());
        survey.setEmployee(employee);
        return survey;

    }
    public EmployeeFarmerSurveyRequestDTO toDto(EmployeeFarmerSurvey survey){
        return new EmployeeFarmerSurveyRequestDTO(

                survey.getFormNumber(),
                survey.getFarmerName(),
                survey.getFarmerMobile(),
                survey.getDistrict(),
                survey.getTaluka(),
                survey.getLandArea(),
                survey.getAddress(),
                survey.getSampleCollected()

        );
    }
}
