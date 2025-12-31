package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employee_farmer_survey",
        indexes = {
                @Index(name = "idx_emp_survey_form_number", columnList = "formNumber"),
                @Index(name = "idx_emp_survey_mobile", columnList = "farmerMobile"),
                @Index(name = "idx_emp_survey_employee_id", columnList = "employee_id"),
                @Index(name = "idx_emp_survey_district", columnList = "district")
        }
)
public class EmployeeFarmerSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;


    private String farmerName;
    private String farmerMobile;
    private String alternateMobile;
    private String place;
    private String village;
    private String taluka;
    private String district;
    @Column(columnDefinition = "TEXT")
    private String address;

    /* ---------- FARM INFO ---------- */
    @Column(columnDefinition = "TEXT")
    private String farmingType; // IRRIGATED / RAIN_FED


    /* ---------- CROP / LIVESTOCK / EQUIPMENT ---------- */
    @Column(columnDefinition = "TEXT")
    private String crops;        // COTTON,SOYBEAN


    @Column(columnDefinition = "TEXT")
    private String livestock;    // COW,BUFFALO


    @Column(columnDefinition = "TEXT")
    private String equipment;    // TRACTOR,PLOUGH


    private Boolean termsAccepted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;

    private String employeeName;

    private LocalDateTime createdAt = LocalDateTime.now();

}