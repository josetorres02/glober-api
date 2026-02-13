package com.jose.primer_api.repository;

import com.jose.primer_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    // Método mágico: Spring crea el SQL "SELECT * FROM usuarios WHERE username = ?"
    Optional<Usuario> findByUsername(String username);
}