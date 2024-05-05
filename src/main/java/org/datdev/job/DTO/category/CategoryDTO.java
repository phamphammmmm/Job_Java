package org.datdev.job.DTO.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank(message = "Name is required")
    public String name;
    public int trending;
    public String imagePreviewPath;
}
