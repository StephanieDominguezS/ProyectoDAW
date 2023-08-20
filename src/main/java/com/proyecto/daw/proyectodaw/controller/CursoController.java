package com.proyecto.daw.proyectodaw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.AlumnoDto;
import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.service.CursoService;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/curso")
    public String homeForm(Model model) {

        model.addAttribute("cursos", cursoService.listarCursos());

        return "curso/home";
    }

    @GetMapping("/curso/editar/{id}")
    public String editarForm(Model model, @PathVariable Optional<Object> id) {

        CursoDto curso = new CursoDto();

        if (id.isPresent() && !id.get().equals("new")) {
            curso = cursoService.buscarPorCodCurso(Long.parseLong(id.get().toString()));
        }

        model.addAttribute("curso", curso);

        return "curso/edit";
    }

    @GetMapping("/curso/delete/{id}")
    public String deleteForm(Model model, @PathVariable Integer id) {

        var curso = cursoService.buscarPorCodCurso(Long.parseLong(id.toString()));

        cursoService.eliminar(curso.getId());

        return "redirect:/curso";
    }

    @PostMapping("/curso/save")
    public String saveForm(Model model, CursoDto curso) {

        cursoService.editar(curso);

        return "redirect:/curso";
    }

}
