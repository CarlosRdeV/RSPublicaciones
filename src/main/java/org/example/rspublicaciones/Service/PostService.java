package org.example.rspublicaciones.Service;

import org.apache.commons.io.FileUtils;
import org.example.rspublicaciones.Entity.Post;
import org.example.rspublicaciones.Repository.PostRepository;
import org.example.rspublicaciones.Repository.UserRepository;
import org.example.rspublicaciones.dto.PostResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class PostService {

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Optional<PostResponseDTO> getPostById(Long id) throws IOException {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isEmpty()) {
            return Optional.empty();
        }

        Post post = postOptional.get();

        PostResponseDTO responseDTO = new PostResponseDTO();
        responseDTO.setId(post.getId());
        responseDTO.setDescription(post.getDescription());
        responseDTO.setUserName(post.getUser().getName());

        if (post.getImagePath() != null) {
            File imageFile = new File(post.getImagePath());
            if (imageFile.exists()) {
                byte[] imageBytes = FileUtils.readFileToByteArray(imageFile);
                String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
                responseDTO.setImageBase64(base64Image);
            }
        }

        return Optional.of(responseDTO);
    }

    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        // Crea el directorio si no existe
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Files.write(filePath, file.getBytes());
        return filePath.toString();
    }
}

