package org.datdev.job.controllers;

import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;
@RestController
@RequestMapping("/file")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping(consumes = "multipart/form-data")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        System.out.println(name);
        return name;
    }
}
