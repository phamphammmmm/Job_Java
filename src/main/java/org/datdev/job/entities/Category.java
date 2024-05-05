package org.datdev.job.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.datdev.job.entities._BaseEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Category extends _BaseEntity {

    @NotBlank(message = "Category name is required.")
    @Column(name = "name")
    private String name = "";

    @Column(name = "trending")
    private int trending;

    @Column(name= "image_Preview_Path")
    private String imagePreviewPath;

    public void setCreated_at(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdated_at(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreated_at() {
        return this.createdAt;
    }

    public LocalDateTime getUpdated_at() {
        return this.updatedAt;
    }
}
