package com.proyecto.daw.proyectodaw.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "nota")
public class Nota implements Serializable{
 

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota", nullable = false)
    private Long idnota;
    
    @Column(name = "id_alumno", nullable = true)
    private Long idAlumno;

    @Column(name = "id_curso", nullable = true)
    private Long idCurso;
    
    @Column(name = "valor_nota", nullable = true)
    private double valorNota;
    
    @Column(name = "periodo", nullable = true)
    private String periodo;
    
}
