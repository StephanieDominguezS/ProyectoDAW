package com.proyecto.daw.proyectodaw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.AlumnoForCursoDto;
import com.proyecto.daw.proyectodaw.service.AlumnoForCursoService;
import com.proyecto.daw.proyectodaw.service.AlumnoService;
import com.proyecto.daw.proyectodaw.service.CursoService;

@Controller
public class AlumnoForCursoControlle {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoForCursoService alumnoForCursoService;

    @GetMapping("/alumno/{id}/curso")
    public String listForCurso(Model model, @PathVariable Integer id) {

        var alumno = alumnoService.obtenerAlumnoPorId(id);

        model.addAttribute("codAlumno", alumno.getCodAlumno());
        model.addAttribute("nombreAlumno", alumno.getNombre());
        model.addAttribute("cursos", alumno.getCursos());

        return "alumno/porCurso";
    }

    @GetMapping("/alumno/{id}/curso/{idCurso}/delete")
    public String deleteForCurso(Model model, @PathVariable Integer id, @PathVariable Integer idCurso) {

        var alumnoPorCursoLis = alumnoForCursoService.findByCodAlumno(id.longValue());

        alumnoPorCursoLis.forEach(alumnoPorCurso -> {
            if (alumnoPorCurso.getCurso().getId().equals(idCurso.longValue())) {
                alumnoForCursoService.eliminar(alumnoPorCurso.getCodAlumnoPorCurso());
                return;
            }
        });

        return "redirect:/alumno/".concat(id.toString()).concat("/curso");
    }

    @GetMapping("/alumno/{id}/curso/add")
    public String addForCurso(Model model, @PathVariable Integer id) {

        AlumnoForCursoDto alumnoForCurso = new AlumnoForCursoDto();

        var alum = alumnoService.obtenerAlumnoPorId(id);
        var listCurso = cursoService.listarCursos();

        alumnoForCurso.setAlumno(alum);

        model.addAttribute("nombreAlumno", alum.getNombre().concat(" ").concat(alum.getApellidos()));
        model.addAttribute("alumnoForCurso", alumnoForCurso);
        model.addAttribute("cursos", listCurso);

        return "alumno/editForCurso";
    }

    @PostMapping("/alumno/curso/save")
    public String deleteForCurso(Model model, AlumnoForCursoDto alumnoForCurso) {

        alumnoForCursoService.guardar(alumnoForCurso);

        return "redirect:/alumno/".concat(alumnoForCurso.getAlumno().getCodAlumno().toString()).concat("/curso");
    }

}
