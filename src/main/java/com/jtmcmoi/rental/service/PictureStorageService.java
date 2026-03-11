package com.jtmcmoi.rental.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.UUID;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
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

    private final Integer serverPort;

    public PictureStorageService(@Value("${server.port:8080}") int serverPort) {
        this.serverPort = serverPort;
    }

    public String storePicture(MultipartFile file) {

        UUID uuid = UUID.randomUUID();

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
                this.uploadDir.resolve(uuid+this.getExtension(file.getContentType())).normalize(),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
        catch ( IOException e ) {
            throw new RuntimeException("Failed to store file", e);
        }
        

        return "http://localhost:"+this.serverPort+"/picture/"+uuid+this.getExtension(file.getContentType());
    }

    public Path resolvePicture(String filename){
        return this.uploadDir.resolve(filename).normalize();
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
