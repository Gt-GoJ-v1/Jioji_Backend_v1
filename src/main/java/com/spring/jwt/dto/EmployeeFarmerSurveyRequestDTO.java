package com.spring.jwt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFarmerSurveyRequestDTO {



    @NotBlank(message = "Farmer name is required")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z ]{2,50}$",
            message = "Farmer name must start with capital letter and contain only letters and spaces"
    )
    @Schema(example = "Ramesh Patil")
    private String farmerName;


    @NotBlank(message = "Farmer mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Please enter valid 10 digit mobile number"
    )
    @Schema(example = "9876543210")
    private String farmerMobile;


    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Please enter valid 10 digit alternate mobile number"
    )
    @Schema(example = "9765432109")
    private String alternateMobile;


    @NotBlank(message = "Place is required")
    @Schema(example = "Near Market Yard")
    private String place;


    @NotBlank(message = "Village is required")
    @Schema(example = "Deulgaon")
    private String village;


    @NotBlank(message = "Taluka is required")
    @Schema(example = "Mulshi")
    private String taluka;


    @NotBlank(message = "District is required")
    @Schema(example = "Pune")
    private String district;


    @NotBlank(message = "Address is required")
    @Schema(example = "Village ABC, Near Main Road")
    private String address;



    @NotBlank(message = "Farming type is required")
    @Schema(
            description = "Type of farming",
            example = "IRRIGATED / RAIN_FED"
    )
    private String farmingType;


    @NotBlank(message = "Crops is required")
    @Schema(
            description = "Comma separated crops",
            example = "COTTON,SOYBEAN"
    )
    private String crops;

    @NotBlank(message = "Live Stock is required")
    @Schema(
            description = "Comma separated livestock",
            example = "COW,BUFFALO"
    )
    private String livestock;

    @NotBlank(message = "Equipment is required")
    @Schema(
            description = "Comma separated equipment",
            example = "TRACTOR,PLOUGH"
    )
    private String equipment;


    @NotNull(message = "Terms acceptance is required")
    @Schema(example = "true")
    private Boolean termsAccepted;
}