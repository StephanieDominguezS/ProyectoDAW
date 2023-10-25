package com.proyecto.daw.proyectodaw.dto;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ProfesorForCursoDto {

    private Long codProfesorPorCuro;

    private ProfesorDto profesor;

    private CursoDto curso;

}
