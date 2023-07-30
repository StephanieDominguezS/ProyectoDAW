package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.UserDao;
import com.proyecto.daw.proyectodaw.domain.User;
import com.proyecto.daw.proyectodaw.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listAll() {
        
        return userDao.findAll();       
    }

    @Override
    public UserDto save(UserDto userDto) {
    	var user = userDao.findByUserName(userDto.getUserName());
    	if (user!=null) {
			return UserDto.builder()
	                .userName(user.getUserName())
	                .password(user.getPassword())                
	                .build();
		}else {
			var resul= userDao.save(User.builder()
					.userName(userDto.getUserName())
					.password(userDto.getPassword())
					.build());
			System.out.println(resul.getIdUser());
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
        return user!=null?UserDto.builder()
                .userName(user.getUserName())
                .password(user.getPassword())                
                .build():UserDto.builder().build();
    }
    
}
