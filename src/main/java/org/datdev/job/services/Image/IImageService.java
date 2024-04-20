package org.datdev.job.services.Image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.CompletableFuture;
@Service
public interface IImageService {
    CompletableFuture<String> saveImage(MultipartFile file, String folderName);
    void deleteImage(String imagePath);
}
