package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.RolDao;
import com.proyecto.daw.proyectodaw.dao.UserDao;
import com.proyecto.daw.proyectodaw.domain.Rol;
import com.proyecto.daw.proyectodaw.domain.User;
import com.proyecto.daw.proyectodaw.dto.UserDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolDao rol;

    @Override
    public List<UserDto> listAll() {
        
        List<UserDto> usersLits = new ArrayList<>();
        
        userDao.findAll().forEach(x->
                            usersLits.add(UserDto.builder()
                                .userName(x.getUserName())
                                .password(x.getPassword())
                                .role(x.getRoles().get(0).getRolName()!=null?x.getRoles().get(0).getRolName():"")                                
                                //.role(rol.findByIdUser(x.getIdUser()).getRolName())
                                .build())
                                );
            
        return usersLits;
    }

    @Override
    public UserDto save(UserDto userDto) {
        var user = userDao.findByUserName(userDto.getUserName());
        if (user != null) {
            return UserDto.builder()
                    .userName(user.getUserName())
                    .password(user.getPassword())
                    .build();
        } else {

            var resul = userDao.save(User.builder()
                    .userName(userDto.getUserName())
                    .password(userDto.getPassword())
                    .build());

            log.info("User saved: " + resul + " With IdUser:" + resul.getIdUser());

            var resulRol = rol.save(Rol.builder()
                    .rolName(userDto.getRole())
                    .user(resul.getIdUser())
                    .build());

            log.info("User saved: " + resul + " With rol:" + resulRol.getRolName());
            return userDto;
        }

    }

    @Override
    public void delete(long id) {

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public UserDto findByUserName(String userName) {

        var user = userDao.findByUserName(userName);
        return user != null ? UserDto.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build() : UserDto.builder().build();
    }

}
