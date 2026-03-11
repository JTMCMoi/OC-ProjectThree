package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RestController;
import com.jtmcmoi.rental.service.PictureStorageService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PictureController {

    private final PictureStorageService pictureStorageService;

    public PictureController(PictureStorageService pictureStorageService) {
        this.pictureStorageService = pictureStorageService;
    }

    @GetMapping("/picture/{filename}")
    public ResponseEntity<Resource> getPicture(@PathVariable String filename) {
        try {
            Path path = this.pictureStorageService.resolvePicture(filename);
            Resource resource = new UrlResource(path.toUri());
            if ( !resource.exists() ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
    

}
