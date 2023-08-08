package com.proyecto.daw.proyectodaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.Curso;

public interface CursoDao extends JpaRepository<Curso, Long>{
    
}
