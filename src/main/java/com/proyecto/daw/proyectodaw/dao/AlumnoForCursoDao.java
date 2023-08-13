package com.proyecto.daw.proyectodaw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.AlumnoForCurso;

public interface AlumnoForCursoDao extends JpaRepository<AlumnoForCurso, Long>{

    public List<AlumnoForCurso> findByIdAlumnoCurso(Long idAlumnoCurso);

    public List<AlumnoForCurso> findByIdAlumno(Long idAlumno);

    public List<AlumnoForCurso> findByIdCurso(Long idCurso); 
    
}
