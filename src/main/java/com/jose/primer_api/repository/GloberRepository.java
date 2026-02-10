package com.jose.primer_api.repository;

import com.jose.primer_api.entity.Glober;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GloberRepository extends JpaRepository<Glober, Long> {
    // Ya vienen los metodos por defecto al usar Jpa gracias a Spring
}