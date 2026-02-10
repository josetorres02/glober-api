package com.jose.primer_api.controller;

import com.jose.primer_api.dto.SquadsRequestDTO;
import com.jose.primer_api.dto.SquadsResponseDTO;
import com.jose.primer_api.service.SquadsService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/squads")
public class SquadsController {

    @Autowired
    private SquadsService service;

    @PostMapping
    public ResponseEntity<SquadsResponseDTO> crear(@Valid @RequestBody SquadsRequestDTO request) {
        SquadsResponseDTO nuevoSquad = service.guardar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSquad);
    }

    @GetMapping
    public ResponseEntity<List<SquadsResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}