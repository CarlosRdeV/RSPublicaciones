package org.example.rspublicaciones.Controller;

import org.example.rspublicaciones.Service.UserService;
import org.example.rspublicaciones.dto.CreateUserRequestDTO;
import org.example.rspublicaciones.dto.CreateUserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequestDTO request) {
        try {
            CreateUserResponseDTO response = userService.createUser(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


