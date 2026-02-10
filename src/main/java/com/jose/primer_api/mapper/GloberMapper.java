package com.jose.primer_api.mapper;

import com.jose.primer_api.dto.GloberRequestDTO;
import com.jose.primer_api.dto.GloberResponseDTO;
import com.jose.primer_api.entity.Glober;
import org.springframework.stereotype.Component;

@Component
public class GloberMapper {

    // Convierte lo que llega (DTO) -> Entidad
    public Glober toEntity(GloberRequestDTO dto) {
        Glober glober = new Glober();
        glober.setName(dto.name());
        glober.setEmail(dto.email());
        glober.setSeniority(dto.seniority());
        glober.setSalary(dto.salary());
        return glober;
    }

    // Convierte la Entidad -> Lo que sale (DTO sin salario)
    public GloberResponseDTO toResponse(Glober entity) {

        String nombreSquad = (entity.getSquad() != null) ? entity.getSquad().getName() : "Sin Asignar";

        return new GloberResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getSeniority(),
            nombreSquad
        );
    }
}