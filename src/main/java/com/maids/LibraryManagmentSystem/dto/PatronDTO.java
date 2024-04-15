package com.maids.LibraryManagmentSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatronDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Address line 1 is required")
    private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "Country is required")
    private String country;
}
