package org.example.rspublicaciones.dto;

import lombok.Data;

@Data
public class PostResponseDTO {
    private Long id;
    private String description;
    private String imageBase64; // Aquí estará la imagen codificada en base64
    private String userName;
}

