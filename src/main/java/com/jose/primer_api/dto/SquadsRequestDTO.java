package com.jose.primer_api.dto;

import jakarta.validation.constraints.*;

public record SquadsRequestDTO(
    @NotBlank(message = "El nombre es obligtorio")
    String name,

    @NotBlank
    String technology
) {}
