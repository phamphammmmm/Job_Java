package org.datdev.job.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Company extends _BaseEntity {
    @NotBlank(message = "Company name is required.")
    @Column(nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name = "";

    @NotBlank
    @Email
    @Size(max = 100)
    private String email = "";

    @NotBlank
    @Size(max = 255)
    private String address = "";

    @Column(name = "description", nullable = false)
    @Size(max = 255)
    private String description = "";

    @NotBlank
    @Size(max = 255)
    private String scale = "";

    @Size(max = 255)
    private String avatarImgPath = "";

    @Size(max = 255)
    private String coverImgPath = "";
}
