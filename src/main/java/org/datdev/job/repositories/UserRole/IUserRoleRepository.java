package org.datdev.job.repositories.UserRole;

import org.datdev.job.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
}
