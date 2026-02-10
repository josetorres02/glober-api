package com.jose.primer_api.service;

import com.jose.primer_api.dto.SquadsRequestDTO;
import com.jose.primer_api.dto.SquadsResponseDTO;
import com.jose.primer_api.entity.Squads;
import com.jose.primer_api.mapper.SquadsMapper;
import com.jose.primer_api.repository.SquadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SquadsService {

    @Autowired
    private SquadsRepository repository;

    @Autowired
    private SquadsMapper mapper;

    public SquadsResponseDTO guardar(SquadsRequestDTO request) {
        Squads entidad = mapper.toEntity(request);
        Squads guardado = repository.save(entidad);
        return mapper.toResponse(guardado);
    }

    public List<SquadsResponseDTO> listarTodos(){
        return repository.findAll()
                        .stream()
                        .map(squads -> mapper.toResponse(squads))
                        .collect(Collectors.toList());
    }
}
