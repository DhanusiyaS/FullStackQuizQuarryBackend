package com.example.quizquarry1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.SystemUser;
import com.example.quizquarry1.service.AuthService;
import com.example.quizquarry1.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final com.example.quizquarry1.service.CustomUserDetailsService userDetailsService;

    public AuthController(JwtService jwtService,
            AuthenticationManager authenticationManager,
            AuthService authService,
            com.example.quizquarry1.service.CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    // @PostMapping("/register")
    // public SystemUser register(@RequestBody SystemUser user) {
    // return authService.register(user);
    // }

    @PostMapping("/register")
    public ResponseEntity<SystemUser> register(
            @RequestBody SystemUser user) {

        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SystemUser user) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPasswordHash()));

            String token = jwtService.generateToken(user.getEmail());

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Login Successful");
            response.put("token", token);
            response.put("role", authService.getRole(user.getEmail()));
            response.put("fullName", authService.getFullName(user.getEmail()));

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex) {

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Invalid Email or Password");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Map<String, Object> response = new HashMap<>();
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("valid", false);
            response.put("message", "Missing or invalid authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = authHeader.substring(7);
        try {
            String username = jwtService.extractUsername(token);
            org.springframework.security.core.userdetails.UserDetails userDetails = 
                userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                response.put("valid", true);
                response.put("username", username);
                response.put("role", authService.getRole(username));
                response.put("fullName", authService.getFullName(username));
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Token is expired, invalid format, or user not found
        }

        response.put("valid", false);
        response.put("message", "Token is expired or invalid");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}