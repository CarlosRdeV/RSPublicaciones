package org.example.rspublicaciones.Controller;

import org.example.rspublicaciones.Entity.Post;
import org.example.rspublicaciones.Entity.User;
import org.example.rspublicaciones.Repository.PostRepository;
import org.example.rspublicaciones.Repository.UserRepository;
import org.example.rspublicaciones.Service.PostService;
import org.example.rspublicaciones.dto.PostResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestParam("userId") Long userId,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        String imagePath = null;
        if (image != null) {
            imagePath = postService.saveImage(image);
        }

        Post post = new Post();
        post.setUser(userOptional.get());
        post.setDescription(description);
        post.setImagePath(imagePath);

        postRepository.save(post);

        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        try {
            return postService.getPostById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

