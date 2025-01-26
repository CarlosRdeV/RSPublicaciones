package org.example.rspublicaciones.dto;

import lombok.Data;

@Data
public class CreateUserResponseDTO {
    private String employeeNumber;
    private String email;
    private String generatedPassword;
}

