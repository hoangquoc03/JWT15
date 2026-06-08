package org.example.jwt15.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.entity.User;
import org.example.jwt15.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public User updateRole(
            Long id,
            String role) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setRole(role);

        return userRepository.save(user);
    }
}