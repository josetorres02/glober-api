package com.jose.primer_api.mapper;

import com.jose.primer_api.dto.SquadsRequestDTO;
import com.jose.primer_api.dto.SquadsResponseDTO;
import com.jose.primer_api.entity.Squads;
import org.springframework.stereotype.Component;

@Component
public class SquadsMapper {
    public Squads toEntity(SquadsRequestDTO dto){
        Squads squads = new Squads();
        squads.setName(dto.name());
        squads.setTechnology(dto.technology());
        return squads;
    }

    public SquadsResponseDTO toResponse(Squads entity){
        return new SquadsResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getTechnology()
        );
    }
}
