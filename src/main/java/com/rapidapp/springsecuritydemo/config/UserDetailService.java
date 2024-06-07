package com.rapidapp.springsecuritydemo.config;

import com.rapidapp.springsecuritydemo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> User.builder().username(user.getEmail())
                        .password(user.getPassword())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
