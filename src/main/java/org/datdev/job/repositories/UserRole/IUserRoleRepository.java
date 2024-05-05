package org.datdev.job.repositories.UserRole;

import jakarta.transaction.Transactional;
import org.datdev.job.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    @Modifying
    @Transactional
    void deleteByUserId(int id);}
