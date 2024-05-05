package org.datdev.job.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User extends _BaseEntity{
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private LocalDateTime birthday; // Nên dùng LocalDateTime cho kiểu dữ liệu date

    @Column(name = "description")
    private String description;

    @Column(name = "is_premium", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isPremium;

    @Setter
    @Column(name = "image_path")
    private String imagePath;

    public double getName() {
        return 0;
    }

    public double getRole() {
        return 0;
    }

    public void setName(String stringCellValue) {
    }

    public void setRole(String stringCellValue) {
    }

}
