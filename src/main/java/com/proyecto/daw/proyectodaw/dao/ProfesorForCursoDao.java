package com.proyecto.daw.proyectodaw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.ProfesorForCurso;

public interface ProfesorForCursoDao extends JpaRepository<ProfesorForCurso, Long> {

    public List<ProfesorForCurso> findAllByIdProfesor(Long id);

    public List<ProfesorForCurso> findAllByIdCurso(Long id);

}
