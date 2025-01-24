package org.example.rspublicaciones.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String imagePath; // Ruta de la imagen almacenada

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


