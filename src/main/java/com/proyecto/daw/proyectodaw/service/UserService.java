package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.domain.User;

public interface UserService {
    
    public List<User> listAll();
    
    public User save(User user);
    
    public void delete(long id);

    public User findByUserName(String userName);

}
