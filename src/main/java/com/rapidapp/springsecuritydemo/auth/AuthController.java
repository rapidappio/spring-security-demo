package com.rapidapp.springsecuritydemo.auth;

import com.rapidapp.springsecuritydemo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
