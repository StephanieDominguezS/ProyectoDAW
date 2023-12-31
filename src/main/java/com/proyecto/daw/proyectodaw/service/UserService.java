package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.UserDto;

public interface UserService {
    
    public List<UserDto> listAll();
    
    public UserDto save(UserDto user);
    
    public void delete(long id);

    public UserDto findByUserName(String userName);

    public UserDto findById(long id);

    public UserDto update(UserDto userDto);

}
