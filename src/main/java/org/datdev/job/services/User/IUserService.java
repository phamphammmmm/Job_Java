package org.datdev.job.services.User;

import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public interface IUserService {
    CompletableFuture<List<Optional<User>>> getAllUsersAsync();

    CompletableFuture<Optional<User>> getUserByIdAsync(int id);

    CompletableFuture<Optional<User>> createUserAsync(UserDTO user);

    CompletableFuture<Optional<User>> updateUserAsync(int id, UserDTO user);

    CompletableFuture<Void> deleteUserAsync(int id);
}
