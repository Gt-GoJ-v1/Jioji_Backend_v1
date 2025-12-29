package com.spring.jwt.repository;

import com.spring.jwt.entity.FarmerSelfie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmerSelfieRepository extends JpaRepository<FarmerSelfie, Long> {

    Optional<FarmerSelfie> findBySurvey_SurveyId(Long surveyId);

    boolean existsBySurvey_SurveyId(Long surveyId);
}
