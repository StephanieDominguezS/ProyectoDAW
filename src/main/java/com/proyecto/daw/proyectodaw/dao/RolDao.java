package com.proyecto.daw.proyectodaw.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long>{

    public Optional<Rol> findByRolName(String rolName);
    
}
