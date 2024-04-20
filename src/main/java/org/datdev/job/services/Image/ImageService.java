package org.datdev.job.services.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ImageService implements IImageService {

    private final Path uploadPath;

    @Autowired
    public ImageService() {
        this.uploadPath = Paths.get("upload").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadPath);
        } catch (Exception e) {
            throw new RuntimeException("Could not create the upload directory!", e);
        }
    }

    @Override
    public CompletableFuture<String> saveImage(MultipartFile file, String folderName) {
        return CompletableFuture.supplyAsync(() -> {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("Invalid file");
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path folderPath = this.uploadPath.resolve(folderName).toAbsolutePath().normalize();
            try {
                Files.createDirectories(folderPath);
                Path filePath = folderPath.resolve(fileName).normalize();
                Files.copy(file.getInputStream(), filePath);
                return "/" + folderName + "/" + fileName;
            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Please try again!", e);
            }
        });
    }

    @Override
    public void deleteImage(String imagePath) {
        Path filePath = this.uploadPath.resolve(imagePath.replace("/", java.io.File.separator)).normalize();
        try {
            Files.delete(filePath);
            System.out.println("Deleted image: " + filePath.toString());
        } catch (Exception e) {
            System.out.println("Error deleting image: " + e.getMessage());
        }
    }
}
