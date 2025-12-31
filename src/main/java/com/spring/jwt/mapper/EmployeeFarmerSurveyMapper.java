package com.spring.jwt.mapper;

import com.spring.jwt.dto.EmployeeFarmerSurveyRequestDTO;
import com.spring.jwt.entity.EmployeeFarmerSurvey;
import com.spring.jwt.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFarmerSurveyMapper {

    public EmployeeFarmerSurvey toEntity(EmployeeFarmerSurveyRequestDTO dto, User employee) {
        EmployeeFarmerSurvey survey = new EmployeeFarmerSurvey();

        survey.setFarmerName(dto.getFarmerName());
        survey.setFarmerMobile(dto.getFarmerMobile());
        survey.setAlternateMobile(dto.getAlternateMobile());
        survey.setPlace(dto.getPlace());
        survey.setVillage(dto.getVillage());
        survey.setTaluka(dto.getTaluka());
        survey.setDistrict(dto.getDistrict());
        survey.setAddress(dto.getAddress());
        survey.setFarmingType(dto.getFarmingType());
        survey.setCrops(dto.getCrops());
        survey.setLivestock(dto.getLivestock());
        survey.setEquipment(dto.getEquipment());
        survey.setTermsAccepted(dto.getTermsAccepted());
        survey.setEmployee(employee);

        // createdAt is auto-set in entity
        return survey;
    }

    public EmployeeFarmerSurveyRequestDTO toDto(EmployeeFarmerSurvey survey) {

        EmployeeFarmerSurveyRequestDTO dto = new EmployeeFarmerSurveyRequestDTO();

        dto.setFarmerName(survey.getFarmerName());
        dto.setFarmerMobile(survey.getFarmerMobile());
        dto.setAlternateMobile(survey.getAlternateMobile());
        dto.setPlace(survey.getPlace());
        dto.setVillage(survey.getVillage());
        dto.setTaluka(survey.getTaluka());
        dto.setDistrict(survey.getDistrict());
        dto.setAddress(survey.getAddress());
        dto.setFarmingType(survey.getFarmingType());
        dto.setCrops(survey.getCrops());
        dto.setLivestock(survey.getLivestock());
        dto.setEquipment(survey.getEquipment());
        dto.setTermsAccepted(survey.getTermsAccepted());

        return dto;

    }
}
