package com.proyecto.daw.proyectodaw.dto;

import java.util.List;

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
public class AlumnoDto {

    private Long codAlumno;
    
    private String nombre;

    private String apellidos;

    private String dni;

    private UserDto user;

    private List<CursoDto> cursos;
    

}
