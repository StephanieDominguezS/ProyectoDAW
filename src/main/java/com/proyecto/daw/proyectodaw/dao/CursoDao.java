package com.proyecto.daw.proyectodaw.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.Curso;

public interface CursoDao extends JpaRepository<Curso, Long>{

    public Optional<Curso> findByIdCurso(Long codCurso);

    public Optional<Curso> findByNombre(String nombre);
    
}
