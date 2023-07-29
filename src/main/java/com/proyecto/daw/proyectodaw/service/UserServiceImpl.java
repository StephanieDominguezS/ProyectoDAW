package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.daw.proyectodaw.dao.UserDao;
import com.proyecto.daw.proyectodaw.domain.User;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listAll() {
        // TODO Auto-generated method stub
        return userDao.findAll();       
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public User findByUserName(String userName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserName'");
    }
    
}
