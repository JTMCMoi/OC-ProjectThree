package com.jtmcmoi.rental.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureStorageService {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
        "image/jpeg",
        "image/png",
        "image/wepp"
    );

    private final Path uploadDir = Paths.get("./pictures").toAbsolutePath().normalize();

    public String storePicture(MultipartFile file, Integer id) {

        if ( file == null || file.isEmpty() ) {
            throw new IllegalArgumentException("Picture is required");
        }

        if ( file.getContentType() == null ||  !ALLOWED_CONTENT_TYPES.contains(file.getContentType()) ) {
            throw new IllegalArgumentException("Invalid picture content-type");
        }

        try {
            Files.createDirectories(this.uploadDir);
            Files.copy(
                file.getInputStream(),
                this.uploadDir.resolve(id+this.getExtension(file.getContentType())).normalize(),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
        catch ( IOException e ) {
            throw new RuntimeException("Failed to store file", e);
        }
        

        return "";
    }

    private String getExtension(String contentType) {
        return switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            default -> "";
        };
    }

}
