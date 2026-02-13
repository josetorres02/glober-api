package com.jose.primer_api.security;

import com.jose.primer_api.entity.Usuario;
import com.jose.primer_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos el usuario en TU base de datos
        Usuario usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 2. Lo convertimos al objeto "UserDetails" que entiende Spring Security
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Pasamos la contraseña encriptada
                .roles(usuario.getRole())
                .build();
    }
}