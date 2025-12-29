package com.spring.jwt.repository;

import com.spring.jwt.entity.EmployeeFarmerSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeFarmerSurveyRepository extends JpaRepository<EmployeeFarmerSurvey, Long> {

    boolean existsByFormNumber(String formNumber);
}
