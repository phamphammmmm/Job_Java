package org.datdev.job.DTO.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    @NotBlank(message = "Fullname is required")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private String phone;

    private LocalDateTime birthday;

    private String description;

    private Boolean isPremium;

//    private MultipartFile avatarImageFile;
}
