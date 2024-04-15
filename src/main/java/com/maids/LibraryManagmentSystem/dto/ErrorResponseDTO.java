package com.maids.LibraryManagmentSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor // to make constructor that accept all arguements as input paramters
public class ErrorResponseDTO {
    private String apiPath;
    private String errorMessage;
    private String errorCode;
    private LocalDateTime errorTime;
}
