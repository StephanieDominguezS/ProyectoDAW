package com.proyecto.daw.proyectodaw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.AlumnoDto;
import com.proyecto.daw.proyectodaw.service.AlumnoService;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;
    
    @GetMapping("/alumnos")
    public String homeForm(Model model) {
    
        model.addAttribute("alumnos", alumnoService.obtenerListaAlumnos());
        
        return "alumno";
    }

    @GetMapping("/alumno/editar/{id}")
    public String editarForm(Model model, @PathVariable Optional<Object> id) {

        AlumnoDto alumno = new AlumnoDto();
        
        if(id.isPresent() && !id.get().equals("new")) {
            alumno = alumnoService.obtenerAlumnoPorId(Integer.parseInt(id.get().toString()));
        }

    
        model.addAttribute("alumno", alumno);
        
        return "alumno/edit";
    }

    @GetMapping("/alumno/delete/{id}")
    public String deleteForm(Model model, @PathVariable Integer id) {

        var alumno = alumnoService.obtenerAlumnoPorId(id);
    
        alumnoService.eliminarAlumno(alumno);
        
        return "redirect:/alumnos";
    }

    @PostMapping("/alumno/save")
    public String saveForm(Model model, AlumnoDto alumno) {

        alumnoService.editarAlumno(alumno);
        
        return "redirect:/alumnos";
    }

}
