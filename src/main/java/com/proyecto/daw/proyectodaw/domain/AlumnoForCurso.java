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
@Table(name = "alumno_for_curso")
public class AlumnoForCurso implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno_for_curso", nullable = false)
    private Long idAlumnoCurso;

    @Column(name = "id_alumno", nullable = false)
    private Long idAlumno;

    @Column(name = "id_curso", nullable = false)
    private Long idCurso;
    
    
}
