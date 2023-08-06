package com.proyecto.daw.proyectodaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long>{

    //Rol findByIdUser(long id);
    
}
