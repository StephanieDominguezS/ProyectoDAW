package com.proyecto.daw.proyectodaw.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.daw.proyectodaw.dto.UserDto;
import com.proyecto.daw.proyectodaw.service.UserService;

@RestController
@RequestMapping("/api-user")
public class UserController {
    
    @Autowired
    private UserService userService;

    //Obtener todos los usuarios
    @GetMapping("/user/all")
    public ResponseEntity<Object> getAllUsers() {
        
        var users = userService.listAll();
        if (users == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(users);
        }        
    }
    

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userName") String userName) {
        
        var user = userService.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(user);
        }        
    }

    @GetMapping("/user/{userName}/password/{password}/valid")
    public ResponseEntity<Boolean> checkPassword(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        
        var user = userService.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(user.getPassword().equals(password));
        }        
    }

    //Crear un nuevo usuario
    @PostMapping("/user/new")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto newUser) {
        
        var user = userService.save(newUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(user);
        }
    }

    
}
