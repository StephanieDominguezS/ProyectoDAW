package com.proyecto.daw.proyectodaw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.dto.ProfesorDto;
import com.proyecto.daw.proyectodaw.dto.ProfesorForCursoDto;
import com.proyecto.daw.proyectodaw.service.CursoService;
import com.proyecto.daw.proyectodaw.service.ProfesorForCursoService;
import com.proyecto.daw.proyectodaw.service.ProfesorService;

@Controller
public class AsginarCursoProfesorController {
    
    @Autowired
    private ProfesorForCursoService profesorForCursoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/asignar-curso-profesor")
    public String homeForm(Model model) {

        var listProfesorCursos= profesorForCursoService.findAll();

        listProfesorCursos.forEach(profesorCurso -> {            
            profesorCurso.setProfesor(profesorService.obtenerProfesorPorId(profesorCurso.getProfesor().getId()));
            profesorCurso.setCurso(cursoService.buscarPorCodCurso(profesorCurso.getCurso().getId()));
        });

        model.addAttribute("profesorCursos", listProfesorCursos);

        return "asignar-curso-profesor/listar";
    }

    @GetMapping("/asignar-curso-profesor/editar/{id}")
    public String editarForm(Model model, @PathVariable Optional<Object> id) {

        ProfesorForCursoDto profesorForCursoDto = new ProfesorForCursoDto();
        profesorForCursoDto.setProfesor(new ProfesorDto());
        profesorForCursoDto.getProfesor().setId(0L);
        profesorForCursoDto.setCurso(new CursoDto());
        profesorForCursoDto.getCurso().setId(0L);


        if (id.isPresent() && !id.get().equals("new")) {

            profesorForCursoDto = profesorForCursoService.findById(Long.parseLong(id.get().toString()));

        }

        model.addAttribute("profesorCurso", profesorForCursoDto);
        model.addAttribute("cursos", cursoService.listarCursos());
        model.addAttribute("profesores", profesorService.obtenerListaProfesores());

        return "asignar-curso-profesor/editar";
    }

    @PostMapping("/asignar-curso-profesor/save")
    public String saveForm(Model model, ProfesorForCursoDto profesorCurso) {
        
        profesorForCursoService.edit(profesorCurso);

        return "redirect:/asignar-curso-profesor";
    }

    @GetMapping("/asignar-curso-profesor/delete/{id}")
    public String deleteForm(Model model, @PathVariable Integer id) {

        var profesorForCurso = profesorForCursoService.findById(Long.parseLong(id.toString()));        

        if (profesorForCurso != null){
            profesorForCursoService.deleteById(profesorForCurso.getCodProfesorPorCuro());
        }

        return "redirect:/asignar-curso-profesor";
    }
}
