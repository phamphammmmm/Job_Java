package org.datdev.job.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "userroles")
@NoArgsConstructor
@ToString
@Data

public class UserRole extends _BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    // Constructors
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
