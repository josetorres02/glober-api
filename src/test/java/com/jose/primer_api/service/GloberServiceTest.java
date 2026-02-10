package com.jose.primer_api.service;

import com.jose.primer_api.dto.GloberRequestDTO;
import com.jose.primer_api.dto.GloberResponseDTO;
import com.jose.primer_api.entity.Glober;
import com.jose.primer_api.entity.Squads;
import com.jose.primer_api.mapper.GloberMapper;
import com.jose.primer_api.repository.GloberRepository;
import com.jose.primer_api.repository.SquadsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*; // Para validar (assertEquals)
import static org.mockito.Mockito.*; // Para simular (when)

@ExtendWith(MockitoExtension.class) // Habilitamos Mockito
class GloberServiceTest {

    // 1. MOCKS (Los actores falsos)
    @Mock
    private GloberRepository globerRepository;

    @Mock
    private SquadsRepository squadsRepository;

    @Mock
    private GloberMapper mapper;

    // 2. EL PROTAGONISTA (Servicio real con actores falsos inyectados)
    @InjectMocks
    private GloberService globerService;

    @Test
    void guardar_DeberiaAsignarSquad_CuandoSquadExiste() {
        // --- ARRANGE (Preparar el escenario) ---
        Long squadId = 1L;

        // El DTO que envía el usuario (Pide unirse a la Squad 1)
        GloberRequestDTO request = new GloberRequestDTO(
                squadId, "Juan", "juan@test.com", "Junior", 1000.0);

        // La Squad que "encontraremos" en la Base de Datos falsa
        Squads squadFalsa = new Squads();
        squadFalsa.setId(squadId);
        squadFalsa.setName("Backend Warriors");

        // El Glober (Entidad) antes de guardar (sin ID)
        Glober globerEntidad = new Glober();
        globerEntidad.setName("Juan");

        // El Glober (Entidad) después de guardar (con ID 100)
        Glober globerGuardado = new Glober();
        globerGuardado.setId(100L);
        globerGuardado.setName("Juan");
        globerGuardado.setSquad(squadFalsa); // ¡Ya tiene squad!

        // La respuesta final que esperamos recibir
        GloberResponseDTO respuestaEsperada = new GloberResponseDTO(
                100L, "Juan", "juan@test.com", "Junior", "Backend Warriors");

        // --- MOCKING (Enseñar el guion a los actores) ---

        // 1. Cuando el mapper convierta el request, devuelve la entidad vacía
        when(mapper.toEntity(request)).thenReturn(globerEntidad);

        // 2. Cuando el repo busque la Squad 1, devuelve la squadFalsa (Simulamos que
        // existe)
        when(squadsRepository.findById(squadId)).thenReturn(Optional.of(squadFalsa));

        // 3. Cuando el repo guarde, devuelve el globerGuardado
        when(globerRepository.save(globerEntidad)).thenReturn(globerGuardado);

        // 4. Cuando el mapper convierta la respuesta, devuelve el DTO final
        when(mapper.toResponse(globerGuardado)).thenReturn(respuestaEsperada);

        // --- ACT (Acción) ---
        GloberResponseDTO resultado = globerService.guardar(request);

        // --- ASSERT (Verificación) ---
        assertNotNull(resultado); // Que no sea nulo
        assertEquals(100L, resultado.id()); // Que tenga el ID simulado
        assertEquals("Backend Warriors", resultado.squadName()); // ¡Que tenga el nombre de la Squad!

        // Verificamos que el servicio buscó la squad en el repo
        verify(squadsRepository, times(1)).findById(squadId);
    }
}