package com.smarttasker.service;


import com.smarttasker.adapter.out.persistence.UserRepository;
import com.smarttasker.domain.model.Users;
import com.smarttasker.dto.auth.LoginRequest;
import com.smarttasker.dto.auth.RegisterRequest;
import com.smarttasker.port.in.AuthService;
import com.smarttasker.port.in.EmailUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailUseCase emailUseCase;


    @Override
    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        // Trigger via Input Port
        emailUseCase.sendWelcomeEmail(user.getEmail(), user.getName());

    }

    @Override
    public String login(LoginRequest request) {
        Users user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.getToken(user.getEmail());
    }
}
