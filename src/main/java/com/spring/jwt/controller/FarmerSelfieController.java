package com.spring.jwt.controller;

import com.spring.jwt.dto.BaseResponseDto;
import com.spring.jwt.service.FarmerSelfieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/farmer-selfie")
@RequiredArgsConstructor
public class FarmerSelfieController {

    private final FarmerSelfieService farmerSelfieService;

    @PostMapping("/upload")
    public ResponseEntity<BaseResponseDto> uploadFarmerSelfie(
            @RequestParam Long surveyId,
            @RequestParam MultipartFile file
    ) {
        return ResponseEntity.ok(
                farmerSelfieService.uploadFarmerSelfie(surveyId, file)
        );
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<BaseResponseDto> getSelfie(
            @PathVariable Long surveyId
    ) {
        return ResponseEntity.ok(
                farmerSelfieService.getSelfieBySurveyId(surveyId)
        );
    }
}
