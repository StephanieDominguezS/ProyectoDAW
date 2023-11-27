package com.proyecto.daw.proyectodaw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.AlumnoDto;
import com.proyecto.daw.proyectodaw.dto.AlumnoForCursoDto;
import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.service.AlumnoForCursoService;
import com.proyecto.daw.proyectodaw.service.AlumnoService;
import com.proyecto.daw.proyectodaw.service.CursoService;

@Controller
public class AsginarCursoAlumnoController {
    
    @Autowired
    private AlumnoForCursoService alumnoForCursoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/asignar-curso-alumno")
    public String homeForm(Model model) {

        var listAlumnoCursos = alumnoForCursoService.findAll();

        listAlumnoCursos.forEach(alumnoCurso -> {
            alumnoCurso.setAlumno(alumnoService.obtenerAlumnoPorId(alumnoCurso.getAlumno().getCodAlumno().intValue()));
            alumnoCurso.setCurso(cursoService.buscarPorCodCurso(alumnoCurso.getCurso().getId()));
        });

        model.addAttribute("alumnoCursos", listAlumnoCursos);

        return "asignar-curso-alumno/listar";
    }

    @GetMapping("/asignar-curso-alumno/editar/{id}")
    public String editarForm(Model model, @PathVariable Optional<Object> id) {

        AlumnoForCursoDto alumnoForCursoDto = new AlumnoForCursoDto();
        alumnoForCursoDto.setAlumno(new AlumnoDto());
        alumnoForCursoDto.getAlumno().setCodAlumno(0L);
        alumnoForCursoDto.setCurso(new CursoDto());
        alumnoForCursoDto.getCurso().setId(0L);

        if (id.isPresent() && !id.get().equals("new")) {
            alumnoForCursoDto = alumnoForCursoService.findById(Long.parseLong(id.get().toString()));
        }

        model.addAttribute("alumnoCurso", alumnoForCursoDto);
        model.addAttribute("cursos", cursoService.listarCursos());
        model.addAttribute("alumnos", alumnoService.obtenerListaAlumnos());

        return "asignar-curso-alumno/editar";
    }

    @PostMapping("/asignar-curso-alumno/save")
    public String saveForm(Model model, AlumnoForCursoDto alumnoForCursoDto) {

        alumnoForCursoService.editar(alumnoForCursoDto);

        return "redirect:/asignar-curso-alumno";

    }

    @GetMapping("/asignar-curso-alumno/delete/{id}")
    public String deleteForm(Model model, @PathVariable Integer id) {

        var alumnoForCurso = alumnoForCursoService.findById(Long.parseLong(id.toString()));

        if (alumnoForCurso != null){
            alumnoForCursoService.eliminar(alumnoForCurso.getCodAlumnoPorCurso());
        }        

        return "redirect:/asignar-curso-alumno";
    }


}
