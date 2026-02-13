package com.jose.primer_api.controller;

import com.jose.primer_api.dto.LoginRequest;
import com.jose.primer_api.dto.AuthResponse;
import com.jose.primer_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // 1. Validar usuario y contraseña con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        // 2. Si pasó la línea anterior, es válido. Generamos el Token.
        String token = jwtUtil.generateToken(request.username());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}