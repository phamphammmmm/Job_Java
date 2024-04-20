package org.datdev.job.DTO.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDTO {
    @NotBlank(message = "Name is required")
    private String Name;
}
