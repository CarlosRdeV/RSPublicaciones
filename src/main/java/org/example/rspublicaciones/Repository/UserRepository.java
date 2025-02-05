package org.example.rspublicaciones.Repository;

import org.example.rspublicaciones.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByEmployeeNumber(String employeeNumber);

    Optional<User> findByEmail(String email);
}
