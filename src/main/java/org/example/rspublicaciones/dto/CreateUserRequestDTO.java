package org.example.rspublicaciones.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserRequestDTO {

    @NotBlank(message = "El número de empleado no puede estar vacío")
    @Pattern(regexp = "\\d{8}", message = "El número de empleado debe contener exactamente 8 dígitos")
    private String employeeNumber;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
}

