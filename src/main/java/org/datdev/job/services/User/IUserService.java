package org.datdev.job.services.User;

import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IUserService {
    CompletableFuture<List<Optional<User>>> getAllUsersAsync();

    CompletableFuture<Optional<User>> getUserByIdAsync(int id);

    CompletableFuture<User> createUserAsync(UserDTO userDTO);

    CompletableFuture<Optional<User>> updateUserAsync(int id, UserDTO user);

    CompletableFuture<Void> deleteUserAsync(int id);
}
