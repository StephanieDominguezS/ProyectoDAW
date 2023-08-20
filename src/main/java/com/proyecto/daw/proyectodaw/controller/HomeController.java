package com.proyecto.daw.proyectodaw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.daw.proyectodaw.service.AlumnoService;
import com.proyecto.daw.proyectodaw.service.CursoService;
import com.proyecto.daw.proyectodaw.service.ProfesorService;

@Controller
public class HomeController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/home")
    public String homeForm(Model model) {

        model.addAttribute("totalCursos", cursoService.listarCursos().size());
        model.addAttribute("totalAlumnos", alumnoService.obtenerListaAlumnos().size());
        model.addAttribute("totalProfesor", profesorService.obtenerListaProfesores().size());
        return "home";
    }
}
