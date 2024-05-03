package org.datdev.job.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Category extends _BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Category name is required.")
    private String name = "";

    @Column(nullable = false)
    private int trending;

    @Column(nullable = true)
    private String qrCodeUrl;

    public double getTrending() {
        return 0;
    }

    public void setCreated_at(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Phương thức setter cho updated_at
    public void setUpdated_at(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Phương thức getter cho created_at
    public LocalDateTime getCreated_at() {
        return this.createdAt;
    }

    // Phương thức getter cho updated_at
    public LocalDateTime getUpdated_at() {
        return this.updatedAt;
    }

    // Thêm setter cho URL mã QR
    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
}
