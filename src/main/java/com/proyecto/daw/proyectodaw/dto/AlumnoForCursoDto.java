package com.proyecto.daw.proyectodaw.dto;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class AlumnoForCursoDto {
    
    private Long codAlumnoPorCurso;

    private AlumnoDto alumno;

    private CursoDto curso;
}
