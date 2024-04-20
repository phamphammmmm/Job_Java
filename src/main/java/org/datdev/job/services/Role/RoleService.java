package org.datdev.job.services.Role;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.role.RoleDTO;
import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.Role;
import org.datdev.job.entities.User;
import org.datdev.job.mappers.IUserMapper;
import org.datdev.job.repositories.Role.IRoleRepository;
import org.datdev.job.repositories.User.IUserRepository;
import org.datdev.job.services.User.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<List<Optional<Role>>> getAllRolesAsync() {
        return CompletableFuture.supplyAsync(() -> roleRepository.findAll()
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Optional<Role>> getRoleByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> roleRepository.findById(id));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Role>> createRoleAsync(Role role){
        return CompletableFuture.supplyAsync(() -> Optional.of(roleRepository.save(role)));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Role>> updateRoleAsync(int id, Role role) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    role.setId(existingRole.getId());
                    return roleRepository.save(role);
                })
                .map(updatedRole -> CompletableFuture.completedFuture(Optional.of(updatedRole)))
                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty()));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteRoleAsync(int id) {
        return CompletableFuture.runAsync(() -> roleRepository.deleteById(id));
    }
}
