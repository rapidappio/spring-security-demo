package com.rapidapp.springsecuritydemo.auth;

import com.rapidapp.springsecuritydemo.config.JwtService;
import com.rapidapp.springsecuritydemo.user.User;
import com.rapidapp.springsecuritydemo.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterRequest registerRequest) {
        User u = User.builder()
                .email(registerRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();
        userRepository.save(u);
    }

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User u = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return jwtService.generateToken(u);

    }
}
