package org.example.labo.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.labo.Domain.DTO.CreateUserDTO;
import org.example.labo.Domain.DTO.KeycloakTokenResponse;
import org.example.labo.Service.iAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo")
public class DemoController {
    private final iAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<KeycloakTokenResponse> register(@RequestBody @Valid CreateUserDTO user) throws Exception {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<KeycloakTokenResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(authService.login(username, password));
    }
    @PreAuthorize("hasRole('role-user')")
    @GetMapping("/auth-test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint is working!");
    }
}