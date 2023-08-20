package com.proyecto.daw.proyectodaw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.ProfesorDto;
import com.proyecto.daw.proyectodaw.service.ProfesorService;

@Controller
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/profesor")
    public String homeForm(Model model) {

        model.addAttribute("profesores", profesorService.obtenerListaProfesores());

        return "profesor/home";
    }

    @GetMapping("/profesor/editar/{id}")
    public String editarForm(Model model, @PathVariable Optional<Object> id) {

        ProfesorDto profesorDto = new ProfesorDto();

        if (id.isPresent() && !id.get().equals("new")) {
            profesorDto = profesorService.obtenerProfesorPorId(Long.parseLong(id.get().toString()));
        }

        model.addAttribute("profesor", profesorDto);

        return "profesor/edit";
    }

    @GetMapping("/profesor/delete/{id}")
    public String deleteForm(Model model, @PathVariable Integer id) {

        var alumno = profesorService.obtenerProfesorPorId(id.longValue());
    
        profesorService.eliminarProfesor(alumno);
        
        return "redirect:/profesor";
    }

    @PostMapping("/profesor/save")
    public String saveForm(Model model, ProfesorDto profesorDto) {

        profesorService.editarProfesor(profesorDto);
        
        return "redirect:/profesor";
    }

}
