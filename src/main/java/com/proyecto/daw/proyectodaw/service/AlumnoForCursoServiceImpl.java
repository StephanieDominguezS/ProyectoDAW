package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.AlumnoForCursoDao;
import com.proyecto.daw.proyectodaw.domain.AlumnoForCurso;
import com.proyecto.daw.proyectodaw.dto.AlumnoDto;
import com.proyecto.daw.proyectodaw.dto.AlumnoForCursoDto;
import com.proyecto.daw.proyectodaw.dto.CursoDto;

@Service
public class AlumnoForCursoServiceImpl implements AlumnoForCursoService {

    @Autowired
    private AlumnoForCursoDao alumnoForCursoRepository;

    @Override
    public AlumnoForCursoDto findById(Long id) {
        var curso = alumnoForCursoRepository.findById(id);
        if (curso.isPresent()) {
            return crearAlumnoForCursoDto(curso.get());
        }
        return AlumnoForCursoDto.builder().build();
    }

    @Override
    public AlumnoForCursoDto guardar(AlumnoForCursoDto alumnoForCurso) {
        var registro = alumnoForCursoRepository.save(
                AlumnoForCurso.builder()
                        .idAlumnoCurso(alumnoForCurso.getCodAlumnoPorCurso())
                        .idAlumno(alumnoForCurso.getAlumno().getCodAlumno())
                        .idCurso(alumnoForCurso.getCurso().getId())
                        .build());
        if (registro.getIdAlumnoCurso() != null) {
            return crearAlumnoForCursoDto(registro);
        }
        return AlumnoForCursoDto.builder().build();
    }

    @Override
    public AlumnoForCursoDto editar(AlumnoForCursoDto alumnoForCurso) {
        var resp = alumnoForCursoRepository.save(
                AlumnoForCurso.builder()
                        .idAlumnoCurso(alumnoForCurso.getCodAlumnoPorCurso())
                        .idAlumno(alumnoForCurso.getAlumno().getCodAlumno())
                        .idCurso(alumnoForCurso.getCurso().getId())
                        .build());

        if (resp.getIdAlumnoCurso() != null) {
            return crearAlumnoForCursoDto(resp);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        alumnoForCursoRepository.deleteById(id);
    }

    @Override
    public List<AlumnoForCursoDto> findByCodCurso(Long codCurso) {
        var cursosInscritos = alumnoForCursoRepository.findAllByIdAlumno(codCurso);
        List<AlumnoForCursoDto> cursosInscritosDto = new ArrayList<>();
        if (cursosInscritos != null) {
            cursosInscritos.forEach(curso -> cursosInscritosDto.add(crearAlumnoForCursoDto(curso)));
        }

        return cursosInscritosDto;
    }

    @Override
    public List<AlumnoForCursoDto> findByCodAlumno(Long codAlumno) {

        var cursosInscritos = alumnoForCursoRepository.findAllByIdAlumno(codAlumno);
        List<AlumnoForCursoDto> cursosInscritosDto = new ArrayList<>();
        if (cursosInscritos != null) {
            cursosInscritos.forEach(curso -> cursosInscritosDto.add(crearAlumnoForCursoDto(curso)));
        }

        return cursosInscritosDto;
    }

    @Override
    public List<AlumnoForCursoDto> findAll() {

        var resp = alumnoForCursoRepository.findAll();
        List<AlumnoForCursoDto> cursosInscritosDto = new ArrayList<>();
        for (AlumnoForCurso curso : resp) {
            cursosInscritosDto.add(crearAlumnoForCursoDto(curso));
        }

        return cursosInscritosDto;

    }

    public AlumnoForCursoDto crearAlumnoForCursoDto(AlumnoForCurso alumnoForCurso) {
        return AlumnoForCursoDto.builder()
                .codAlumnoPorCurso(alumnoForCurso.getIdAlumnoCurso())
                .alumno(AlumnoDto.builder().codAlumno(alumnoForCurso.getIdAlumno()).build())
                .curso(CursoDto.builder().id(alumnoForCurso.getIdCurso()).build())
                .build();
    }

}
