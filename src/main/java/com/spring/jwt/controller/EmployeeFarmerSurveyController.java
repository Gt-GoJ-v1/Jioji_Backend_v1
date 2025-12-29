package com.spring.jwt.controller;

import com.spring.jwt.dto.BaseResponseDto;
import com.spring.jwt.dto.EmployeeFarmerSurveyRequestDTO;
import com.spring.jwt.service.EmployeeFarmerSurveyService;
import com.spring.jwt.utils.BaseResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
public class EmployeeFarmerSurveyController {


    private final EmployeeFarmerSurveyService employeeFarmerSurveyService;

        @PostMapping("/submit")
        public ResponseEntity<BaseResponseDTO> submitSurvey(@Valid @RequestBody EmployeeFarmerSurveyRequestDTO dto, Authentication authentication) {
            BaseResponseDTO response = employeeFarmerSurveyService.submitSurvey(dto, authentication);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/{surveyId}")
                public ResponseEntity<BaseResponseDto> getSurveyById(@RequestParam Long surveyId){
            BaseResponseDto response = employeeFarmerSurveyService.getSurveyById(surveyId);
            return ResponseEntity.ok(response);
        }
    @GetMapping
    public ResponseEntity<Page<EmployeeFarmerSurveyRequestDTO>> getSurveys(
            @RequestParam(required = false) String formNumber,
            @RequestParam(required = false) String farmerName,
            @RequestParam(required = false) String taluka,
            @RequestParam(required = false) String district,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok(
                employeeFarmerSurveyService.getSurveys(
                        formNumber,
                        farmerName,
                        taluka,
                        district,
                        pageable
                )
        );
    }





}
