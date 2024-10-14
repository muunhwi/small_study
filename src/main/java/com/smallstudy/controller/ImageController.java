package com.smallstudy.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    @Value("${file.dir}")
    private String basePath;
    private static final String IMAGE_SUB_STRING_PATH = "/image/";

    @GetMapping("/image/**")
    public ResponseEntity<byte[]> getProfileImage(HttpServletRequest request) throws IOException {

        String filePath = request.getRequestURI().substring(IMAGE_SUB_STRING_PATH.length());
        Path path = Paths.get(basePath, filePath);

        if(!Files.exists(path))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String mimeType = Files.probeContentType(path);
        byte[] imageBytes = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(mimeType));

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}
