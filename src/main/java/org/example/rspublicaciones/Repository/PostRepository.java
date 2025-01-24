package org.example.rspublicaciones.Repository;

import org.example.rspublicaciones.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
