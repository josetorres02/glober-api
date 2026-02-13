package com.jose.primer_api;

import com.jose.primer_api.entity.Usuario;
import com.jose.primer_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Si no hay usuarios, creamos uno por defecto
        if (userRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123")); // <--- ¡AQUÍ ENCRIPTAMOS!
            admin.setRole("ADMIN");
            
            userRepository.save(admin);
            System.out.println("✅ Usuario ADMIN creado: admin / 123");
        }
    }
}