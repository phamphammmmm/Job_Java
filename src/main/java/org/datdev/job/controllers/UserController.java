package org.datdev.job.controllers;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.User;
import org.datdev.job.services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<User>>>> getAllUsers() {
        return userService.getAllUsersAsync()
                .thenApply(users -> new ResponseEntity<>(users, HttpStatus.OK));
    }

//    @GetMapping("/{id}")
//    public CompletableFuture<ResponseEntity<Optional<User>>> getUserById(@PathVariable int id) {
//        return userService.getUserByIdAsync(id)
//                .thenApply(user -> user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
//                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
//    }

    @PostMapping
    public CompletableFuture<ResponseEntity<User>> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUserAsync(userDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<User>> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userService.updateUserAsync(id, userDTO)
                .thenApply(user -> user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteUser(@PathVariable int id) {
        return userService.deleteUserAsync(id)
                .thenApply(ignored -> ResponseEntity.noContent().build());
    }
}
