package org.example.rspublicaciones.Repository;

import org.example.rspublicaciones.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {


}
