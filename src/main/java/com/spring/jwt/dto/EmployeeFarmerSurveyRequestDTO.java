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

    @NotBlank(message = "Form Number is required")
    @Schema(description = "Form No Must Be Unique")
    private String formNumber;

    @NotBlank(message = "Farmer name is required")
    @Pattern(
            regexp = "^[A-Z][A-Za-z ]{2,50}$",
            message = "Farmer name must starts with capital letters and contain only letter and spaces(2-50 characters)"
    )
    @Schema(
            description = "Name of the farmer",
            example = "Ramesh Patil"
    )
    private String farmerName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Please Enter Valid 10 digit Mobile No, e.g 7665671265"
    )
    @Schema(
            description = "Enter 10 digit Valid Mobile No",
            example = "9766431234"
    )
    private String farmerMobile;

    @NotBlank(message = "Farmer area is required")
    @Pattern(
            regexp = "^\\d+(\\.\\d{1,2})?$",
            message = "Land area must be a valid number(example 2 or 2.5)"
    )
    @Schema(
            description = "Total land area owned by farmer(in Acres)",
            example = "2.5"
    )
    private String landArea;

    @NotBlank(message = "Address is Required")
    @Schema(
            description = "Complete Residential Address Of Farmer",
            example = "Village Abc , Near Main Road"
    )
    private String address;

    @NotBlank(message = "Taluka is Requied")
    @Schema(
            description = "Name of Taluka",
            example = "Mulshi"
    )
    private String taluka;

    @NotBlank(message = "District is Required")
    @Schema(
            description = "Name of District",
            example = "Pune"
    )
    private String district;

    @NotNull(message = "Sample Status is required")
    private Boolean sampleCollected;




}
