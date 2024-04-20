package org.datdev.job.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Tag extends _BaseEntity {
    @NotBlank(message = "Name is required")
    private String name;
}
