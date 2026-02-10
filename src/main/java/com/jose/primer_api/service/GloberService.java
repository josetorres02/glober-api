package com.jose.primer_api.service;

import com.jose.primer_api.dto.GloberRequestDTO;
import com.jose.primer_api.dto.GloberResponseDTO;
import com.jose.primer_api.entity.Glober;
import com.jose.primer_api.entity.Squads;
import com.jose.primer_api.mapper.GloberMapper;
import com.jose.primer_api.repository.GloberRepository;
import com.jose.primer_api.repository.SquadsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GloberService {

    @Autowired
    private GloberRepository repository;

    @Autowired
    private SquadsRepository squadsRepository;

    @Autowired
    private GloberMapper mapper;

    public GloberResponseDTO guardar(GloberRequestDTO request) {
        // 1. Convertir DTO a Glober (viene sin Squad)
        Glober glober = mapper.toEntity(request);

        // 2. BUSCAR LA SQUAD (Si el ID no es nulo)
        if (request.squadId() != null) {
            // Buscamos la Squad por ID. Si no existe, lanzamos error o null.
            Squads squadEncontrada = squadsRepository.findById(request.squadId())
                    .orElse(null); // O podrías lanzar una excepción personalizada

            // 3. ASIGNAR LA RELACIÓN
            glober.setSquad(squadEncontrada);
        }

        // 4. Guardar (Spring guarda el Glober Y la relación con la Squad)
        Glober guardado = repository.save(glober);

        return mapper.toResponse(guardado);
    }

    public List<GloberResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(glober -> mapper.toResponse(glober))
                .collect(Collectors.toList());
    }
}