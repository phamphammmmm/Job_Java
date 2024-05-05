package org.datdev.job.services.User;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.Role;
import org.datdev.job.entities.User;
import org.datdev.job.entities.UserRole;
import org.datdev.job.mappers.IUserMapper;
import org.datdev.job.repositories.Role.IRoleRepository;
import org.datdev.job.repositories.User.IUserRepository;
import org.datdev.job.repositories.UserRole.IUserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserRoleRepository userRoleRepository;

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
    public CompletableFuture<User> createUserAsync(UserDTO userDTO) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                // Chuyển đổi DTO thành entity và lưu vào cơ sở dữ liệu
                User user = IUserMapper.INSTANCE.toEntity(userDTO);
                user = userRepository.save(user);

                // Lưu vai trò cho người dùng
                Role seekerRole = roleRepository.findByName("Seeker")
                        .orElseThrow(() -> new RuntimeException("Seeker role not found"));
                UserRole userRole = new UserRole(user, seekerRole);
                userRoleRepository.save(userRole);

                return user;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error creating user: " + e.getMessage());
            }
        });
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
    public CompletableFuture<Void> deleteUserAsync(int id) {
        return CompletableFuture.runAsync(() -> {
            userRoleRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        });
    }

    private String saveAvatarImage(MultipartFile avatarImageFile) {
        try {
            if (avatarImageFile != null && !avatarImageFile.isEmpty()) {
                // Xác định đường dẫn thư mục avatar
                String resourcesPath = "D:/Code/Java/joboard/job/src/main/resources";
                String avatarDirectory = Paths.get(resourcesPath, "avatar").toString();

                Files.createDirectories(Paths.get(avatarDirectory));

                String fileName = UUID.randomUUID().toString() + ".png";

                // Xác định đường dẫn của tệp mới
                Path imagePath = Paths.get(avatarDirectory, fileName);

                // Ghi nội dung của avatarImageFile vào tệp mới
                Files.write(imagePath, avatarImageFile.getBytes());

                // Trả về đường dẫn của ảnh đã lưu
                return imagePath.toString().replace("\\", "/");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    @Transactional
//    public CompletableFuture<String> uploadFileFromBase64(String base64String, String extension) {
//            CompletableFuture<String> future = new CompletableFuture<>();
//
//            if (base64String == null || base64String.isEmpty()) {
//                future.complete(null);
//                return future;
//            }
//
//            // Xóa phần tiêu đề của chuỗi base64 (ví dụ: "data:image/png;base64,")
//            base64String = base64String.replace("data:image/png;base64,", "");
//
//            // Giải mã chuỗi base64 thành mảng byte
//            byte[] bytes = Base64.getDecoder().decode(base64String);
//
//            // Xác định đường dẫn thư mục avatar
//            String resourcesPath = "D:/Code/Java/joboard/job/src/main/resources";
//            String avatarDirectory = Paths.get(resourcesPath, "avatar").toString();
//
//            // Tạo thư mục avatar nếu nó không tồn tại
//            File avatarDir = new File(avatarDirectory);
//            if (!avatarDir.exists()) {
//                avatarDir.mkdirs();
//            }
//
//            // Tạo tên tệp ngẫu nhiên với phần mở rộng được chỉ định
//            String fileName = UUID.randomUUID().toString() + extension;
//
//            // Xác định đường dẫn của tệp mới
//            String filePath = Paths.get(avatarDirectory, fileName).toString();
//
//            try {
//                // Ghi mảng byte vào tệp
//                Files.write(Paths.get(filePath), bytes);
//                future.complete(Paths.get("avatar", fileName).toString().replace("\\", "/")); // Trả về đường dẫn tới ảnh đã lưu
//            } catch (IOException e) {
//                e.printStackTrace();
//                future.complete(null);
//            }
//
//            return future;
//    }
}
