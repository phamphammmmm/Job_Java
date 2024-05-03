package org.datdev.job.services.Role;

import org.datdev.job.DTO.role.RoleDTO;
import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.Role;
import org.datdev.job.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IRoleService {
    CompletableFuture<List<Optional<Role>>> getAllRolesAsync();

    CompletableFuture<Optional<Role>> getRoleByIdAsync(int id);

    CompletableFuture<Optional<Role>> createRoleAsync(RoleDTO roleDto);

    CompletableFuture<Optional<Role>> updateRoleAsync(int id, Role role);

    CompletableFuture<Void> deleteRoleAsync(int id);
}
