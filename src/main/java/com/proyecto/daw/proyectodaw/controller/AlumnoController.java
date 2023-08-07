package com.proyecto.daw.proyectodaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlumnoController {
    
    @GetMapping("/alumnos")
    public String homeForm(Model model) {
        
        return "alumno";
    }

}
