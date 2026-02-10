package com.jose.primer_api.controller;

import com.jose.primer_api.dto.GloberRequestDTO;
import com.jose.primer_api.dto.GloberResponseDTO;
import com.jose.primer_api.service.GloberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/globers")
public class GloberController {

    @Autowired
    private GloberService service;

    @PostMapping
    public ResponseEntity<GloberResponseDTO> crear(@Valid @RequestBody GloberRequestDTO request) {
        GloberResponseDTO nuevoGlober = service.guardar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGlober);
    }

    @GetMapping
    public ResponseEntity<List<GloberResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}