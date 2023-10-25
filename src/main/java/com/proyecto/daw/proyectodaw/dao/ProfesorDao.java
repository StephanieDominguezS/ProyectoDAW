package com.proyecto.daw.proyectodaw.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.Profesor;

public interface ProfesorDao extends JpaRepository<Profesor, Long> {

    public Optional<Profesor> findByNombres(String nombres);

    public Optional<Profesor> findByApellidos(String apellidos);

    public Optional<Profesor> findByDni(String dni);
}
