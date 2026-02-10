package com.jose.primer_api.dto;

public record GloberResponseDTO(
    Long id,
    String name,
    String email,
    String seniority,
    String squadName
) {}