package com.ansari.todo.service.auth;

import com.ansari.todo.dto.auth.AuthRequestDto;
import com.ansari.todo.dto.auth.AuthResponseDto;
import com.ansari.todo.dto.auth.RegisterRequestDto;
import com.ansari.todo.entity.User;
import com.ansari.todo.exception.DuplicateResourceException;
import com.ansari.todo.exception.ResourceNotFoundException;
import com.ansari.todo.mapper.UserMapper;
import com.ansari.todo.repository.auth.UserRepository;
import com.ansari.todo.security.MongoUserDetailsService;
import com.ansari.todo.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    public AuthResponseDto registerUser(RegisterRequestDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Username already exists!");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singletonList("USER"));
        userRepository.save(user);

        // Generate JWT
        UserDetails userDetails = mongoUserDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtService.generateToken(userDetails, user.getId());

        return new AuthResponseDto(user.getId(), user.getUsername(), user.getEmail(), jwtToken);
    }

    @Override
    public AuthResponseDto registerAdmin(RegisterRequestDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Username already exists!");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singletonList("ADMIN"));
        userRepository.save(user);

        // Generate JWT
        UserDetails userDetails = mongoUserDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtService.generateToken(userDetails, user.getId());
        return new AuthResponseDto(user.getId(), user.getUsername(), user.getEmail(), jwtToken);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            if (authentication.isAuthenticated()) {
                UserDetails userDetails = mongoUserDetailsService.loadUserByUsername(dto.getUsername());
                if (userDetails instanceof User user) {
                    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                        throw new ResourceNotFoundException("Invalid credentials");
                    }
                    String jwtToken = jwtService.generateToken(userDetails, user.getId());
                    return new AuthResponseDto(user.getId(), user.getUsername(), user.getEmail(), jwtToken);
                } else {
                    throw new BadCredentialsException("Invalid user type");
                }
            }
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException("Invalid credentials");
        } catch (AuthenticationException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        return null;
    }

    @Override
    public void logout() {

    }
}