package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.AlumnoDao;
import com.proyecto.daw.proyectodaw.domain.Alumno;
import com.proyecto.daw.proyectodaw.dto.AlumnoDto;
import com.proyecto.daw.proyectodaw.handler.AlumnoException;
import com.proyecto.daw.proyectodaw.util.UtilService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoDao alumnoDao;

    @Autowired
    private UtilService utilService;

    @Override
    public AlumnoDto obtenerAlumnoPorNombre(String nombre) {

        var alumno = alumnoDao.findByNombre(nombre);

        if (alumno.getIdAlumno() != null) {

            return AlumnoDto.builder()
                    .codAlumno(alumno.getIdAlumno())
                    .nombre(alumno.getNombre())
                    .apellidos(alumno.getApellido())
                    .user(alumno.getIdUser()!=null?utilService.obtenerUsuarioPorId(alumno.getIdUser()):null)
                    .cursos(utilService.listarCursosPorAlumno(alumno.getIdAlumno()))
                    .build();
        }

        throw new AlumnoException("No se ha encontrado el alumno");
    }

    @Override
    public AlumnoDto obtenerAlumnoPorDni(String dni) {

        var alumno = alumnoDao.findByDni(dni);

        if (alumno.getIdAlumno() != null) {

            return AlumnoDto.builder()
                    .codAlumno(alumno.getIdAlumno())
                    .nombre(alumno.getNombre())
                    .apellidos(alumno.getApellido())
                    .user(alumno.getIdUser()!=null?utilService.obtenerUsuarioPorId(alumno.getIdUser()):null)
                    .build();
        }

        throw new AlumnoException("No se ha encontrado el alumno");
    }

    @Override
    public List<AlumnoDto> obtenerListaAlumnos() {
        var alumnos = alumnoDao.findAll();
        List<AlumnoDto> alumnosDto = new ArrayList<>();
        for (var alumno : alumnos) {
            alumnosDto.add(AlumnoDto.builder()
                    .nombre(alumno.getNombre())
                    .apellidos(alumno.getApellido())
                    .build());
        }
        return alumnosDto;
    }

    @Override
    public AlumnoDto agregarAlumno(AlumnoDto alumno) {

        var response = alumnoDao.save(Alumno.builder()
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellidos())
                .dni(alumno.getDni())
                .build());
        if (response.getIdAlumno() != null) {
            return AlumnoDto.builder()
                    .codAlumno(response.getIdAlumno())
                    .nombre(response.getNombre())
                    .apellidos(response.getApellido())
                    .build();
        }
        throw new AlumnoException("No se ha podido guardar el alumno");

    }

    @Override
    public AlumnoDto editarAlumno(AlumnoDto alumno) {
        var response = alumnoDao.save(Alumno.builder()
                .idAlumno(alumno.getCodAlumno())
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellidos())
                .dni(alumno.getDni())
                .build());
        if (response.getIdAlumno() != null) {
            return AlumnoDto.builder()
                    .codAlumno(response.getIdAlumno())
                    .nombre(response.getNombre())
                    .apellidos(response.getApellido())
                    .build();
        }

        throw new AlumnoException("No se ha podido Actualizar el alumno");
    }

    @Override
    public void eliminarAlumno(AlumnoDto alumno) {

        var alumnoOptional = alumnoDao.findById(alumno.getCodAlumno());
        if (alumnoOptional.isPresent()) {
            alumnoDao.delete(alumnoOptional.get());
        } else {
            throw new AlumnoException("No se ha podido eliminar el alumno");
        }
    }

}
