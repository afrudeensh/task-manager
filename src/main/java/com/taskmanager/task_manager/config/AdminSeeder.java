package com.taskmanager.task_manager.config;

import com.taskmanager.task_manager.entity.User;
import com.taskmanager.task_manager.enums.UserRole;
import com.taskmanager.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.existsByEmail("admin@taskmanager.com")) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@taskmanager.com");
        admin.setPassword(passwordEncoder.encode("ChangeMe123!"));
        admin.setRole(UserRole.ADMIN);

        userRepository.save(admin);
    }
}