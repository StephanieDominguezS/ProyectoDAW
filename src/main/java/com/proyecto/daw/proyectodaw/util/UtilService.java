package com.proyecto.daw.proyectodaw.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.dto.UserDto;
import com.proyecto.daw.proyectodaw.service.AlumnoForCursoService;
import com.proyecto.daw.proyectodaw.service.CursoService;
import com.proyecto.daw.proyectodaw.service.ProfesorForCursoService;
import com.proyecto.daw.proyectodaw.service.UserService;

@Component
public class UtilService {

    @Autowired
    private UserService userService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoForCursoService alumnoForCursoService;

    @Autowired
    private ProfesorForCursoService profesorForCursoService;

    public List<CursoDto> listarCursosPorAlumno(Long idAlumno) {

        var resp = alumnoForCursoService.findByCodAlumno(idAlumno);
        List<CursoDto> lista = new ArrayList<>();

        if (resp != null && !resp.isEmpty()) {
            for (var curso : resp) {

                var cursoRestServ = cursoService.buscarPorCodCurso(curso.getCurso().getId());

                lista.add(CursoDto.builder()
                        .id(curso.getCurso().getId())
                        .nombre(cursoRestServ.getNombre())
                        .descripcion(cursoRestServ.getDescripcion())
                        .horario(cursoRestServ.getHorario())
                        .build());

            }
        }

        return lista;
    }

    public UserDto obtenerUsuarioPorId(Long id) {
        return userService.findById(id);
    }

    public List<CursoDto> listarCursosPorProfesor(Long idProfesor) {

        var resp = profesorForCursoService.findByProfesor(idProfesor);
        List<CursoDto> lista = new ArrayList<>();

        if (resp != null && !resp.isEmpty()) {
            for (var curso : resp) {

                var cursoRestServ = cursoService.buscarPorCodCurso(curso.getCurso().getId());

                lista.add(CursoDto.builder()
                        .id(curso.getCurso().getId())
                        .nombre(cursoRestServ.getNombre())
                        .descripcion(cursoRestServ.getDescripcion())
                        .horario(cursoRestServ.getHorario())
                        .build());

            }
        }

        return lista;
    }

}
