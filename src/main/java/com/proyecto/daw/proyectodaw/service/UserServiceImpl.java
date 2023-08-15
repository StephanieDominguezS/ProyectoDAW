package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.UserDao;
import com.proyecto.daw.proyectodaw.domain.User;
import com.proyecto.daw.proyectodaw.dto.UserDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolService rolService;

    @Override
    public List<UserDto> listAll() {

        List<UserDto> usersLits = new ArrayList<>();

        var resp = userDao.findAll();

        for (User user : resp) {
            usersLits.add(createUserDto(user));
        }

        return usersLits;
    }

    @Override
    public UserDto save(UserDto userDto) {

        var resul = userDao.save(User.builder()
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .idRol(userDto.getRole() != null ? userDto.getRole().getId() : null)
                .build());

        if (resul.getIdUser() != null) {
            return createUserDto(resul);
        }

        throw new UnsupportedOperationException("Ocurrio un erro al intetar agregar al usuario");

    }

    @Override
    public void delete(long id) {

        userDao.delete(
                userDao.findById(id).orElseThrow(() -> new UnsupportedOperationException("No se encontro el usuario")));
    }

    @Override
    public UserDto findByUserName(String userName) {

        var user = userDao.findByUserName(userName);
        return user != null ? createUserDto(user) : UserDto.builder().build();
    }

    @Override
    public UserDto findById(long id) {
        var resp = userDao.findById(id);
        return resp.isPresent() ? createUserDto(resp.get()) : UserDto.builder().build();
    }
   
    @Override
    public UserDto update(UserDto userDto) {
        
        var resul = userDao.save(User.builder()
                .idUser(userDto.getId())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .idRol(userDto.getRole() != null ? userDto.getRole().getId() : null)
                .build());

        if (resul.getIdUser() != null) {
            return createUserDto(resul);
        }
        throw new UnsupportedOperationException("Ocurrio un erro al intetar actualizar al usuario");
    }

     public UserDto createUserDto(User user) {
        return UserDto.builder()
                .id(user.getIdUser())
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getIdRol() != null ? rolService.findRolById(user.getIdRol()) : null)
                .build();
    }

}
