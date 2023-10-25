package com.proyecto.daw.proyectodaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.Alumno;

public interface AlumnoDao extends JpaRepository<Alumno, Long>{

    public Alumno findByNombre(String nombre);

    public Alumno findByDni(String dni);
    
}
