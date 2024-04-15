package com.maids.LibraryManagmentSystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class JwtResponseDTO {
    private String token;

}
