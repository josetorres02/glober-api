package com.jose.primer_api.dto;

import jakarta.validation.constraints.*;

public record GloberRequestDTO(

        Long squadId,

        @NotBlank(message = "El nombre es obligatorio") String name,

        @Email(message = "El email no es válido") @NotBlank String email,

        @NotBlank String seniority,

        @NotNull @Positive Double salary) {
}