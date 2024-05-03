package org.datdev.job.controllers;

import lombok.RequiredArgsConstructor;
import org.datdev.job.entities.Role;
import org.datdev.job.services.Role.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<Role>>>> getAllRoles() {
        return roleService.getAllRolesAsync()
                .thenApply(roles -> new ResponseEntity<>(roles, HttpStatus.OK));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Role>> createRole(@RequestBody Role role) {
        return roleService.createRoleAsync(role)
                .thenApply(createdRole -> ResponseEntity.ok(createdRole.orElse(null)))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Role>> updateRole(@PathVariable int id, @RequestBody Role role) {
        return roleService.updateRoleAsync(id, role)
                .thenApply(updatedRole -> updatedRole.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteRole(@PathVariable int id) {
        return roleService.deleteRoleAsync(id)
                .thenApply(ignored -> ResponseEntity.noContent().build());
    }
}
