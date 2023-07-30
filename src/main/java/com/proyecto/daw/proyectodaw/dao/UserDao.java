package com.proyecto.daw.proyectodaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.daw.proyectodaw.domain.User;


public interface UserDao extends JpaRepository<User, Long>{
   
    User findByUserName(String userName);

}
