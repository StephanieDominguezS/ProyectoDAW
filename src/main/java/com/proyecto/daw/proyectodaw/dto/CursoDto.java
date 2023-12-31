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
public class CursoDto {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private String horario;
}
