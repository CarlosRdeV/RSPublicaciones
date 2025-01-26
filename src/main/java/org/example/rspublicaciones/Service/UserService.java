package org.example.rspublicaciones.Service;

import org.example.rspublicaciones.Entity.User;
import org.example.rspublicaciones.Repository.UserRepository;
import org.example.rspublicaciones.Util.PasswordGenerator;
import org.example.rspublicaciones.dto.CreateUserRequestDTO;
import org.example.rspublicaciones.dto.CreateUserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponseDTO createUser(CreateUserRequestDTO request) {
        // Validar existencia previa
        if (userRepository.findByEmployeeNumber(request.getEmployeeNumber()).isPresent()) {
            throw new IllegalArgumentException("El número de empleado ya está registrado.");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        // Generar la contraseña aleatoria
        String randomPassword = PasswordGenerator.generateRandomPassword();

        // Crear y guardar el usuario
        User user = new User();
        user.setEmployeeNumber(request.getEmployeeNumber());
        user.setEmail(request.getEmail());
        user.setPassword(randomPassword); // Guarda la contraseña
        user.setName(request.getName());
        userRepository.save(user);

        // Retornar la respuesta con el password generado
        CreateUserResponseDTO response = new CreateUserResponseDTO();
        response.setEmployeeNumber(user.getEmployeeNumber());
        response.setEmail(user.getEmail());
        response.setGeneratedPassword(randomPassword);

        return response;
    }
}

