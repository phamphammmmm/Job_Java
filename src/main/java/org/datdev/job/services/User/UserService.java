package org.datdev.job.services.User;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.User;
import org.datdev.job.mappers.IUserMapper;
import org.datdev.job.repositories.User.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<List<Optional<User>>> getAllUsersAsync() {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll()
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Optional<User>> getUserByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> userRepository.findById(id));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<User>> createUserAsync(UserDTO userDTO) {
        User user = IUserMapper.INSTANCE.toEntity(userDTO);
        return CompletableFuture.supplyAsync(() -> Optional.of(userRepository.save(user)));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<User>> updateUserAsync(int id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    IUserMapper.INSTANCE.toEntity(userDTO);
                    return userRepository.save(user);
                })
                .map(user -> CompletableFuture.completedFuture(Optional.of(user)))
                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty()));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteUserAsync(int id) {
        return CompletableFuture.runAsync(() -> userRepository.deleteById(id));
    }
}
