package org.example.rspublicaciones.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users") // Cambia el nombre de la tabla aquí
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

