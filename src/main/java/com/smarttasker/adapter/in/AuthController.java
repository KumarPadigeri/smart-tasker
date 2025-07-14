package com.smarttasker.adapter.in;


import com.smarttasker.adapter.out.persistence.UserRepository;
import com.smarttasker.domain.model.Users;
import com.smarttasker.dto.auth.AuthResponse;
import com.smarttasker.dto.auth.LoginRequest;
import com.smarttasker.dto.auth.RegisterRequest;
import com.smarttasker.dto.auth.User;
import com.smarttasker.port.in.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {


    private final AuthService authService;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(new User(user.getName(), user.getEmail()));
    }


}
