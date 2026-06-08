package org.example.jwt15.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.UpdateRoleRequest;
import org.example.jwt15.entity.User;
import org.example.jwt15.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User me(
            Authentication authentication) {

        return userService.getCurrentUser(
                authentication.getName());
    }

    @PutMapping("/{id}/role")
    public User updateRole(
            @PathVariable Long id,
            @RequestBody UpdateRoleRequest request) {

        return userService.updateRole(
                id,
                request.getRole());
    }
}