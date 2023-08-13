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
            return AlumnoForCursoDto.builder()
                    .codAlumnoPorCurso(curso.get().getIdAlumnoCurso())
                    .alumno(AlumnoDto.builder().codAlumno(curso.get().getIdAlumno()).build())
                    .curso(CursoDto.builder().id(curso.get().getIdCurso()).build())
                    .build();
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
            return AlumnoForCursoDto.builder()
                    .codAlumnoPorCurso(registro.getIdAlumnoCurso())
                    .alumno(AlumnoDto.builder().codAlumno(registro.getIdAlumno()).build())
                    .curso(CursoDto.builder().id(registro.getIdCurso()).build())
                    .build();
        }
        return AlumnoForCursoDto.builder().build();
    }

    @Override
    public void editar(AlumnoForCursoDto alumnoForCurso) {
        alumnoForCursoRepository.save(
                AlumnoForCurso.builder()
                        .idAlumnoCurso(alumnoForCurso.getCodAlumnoPorCurso())
                        .idAlumno(alumnoForCurso.getAlumno().getCodAlumno())
                        .idCurso(alumnoForCurso.getCurso().getId())
                        .build());
    }

    @Override
    public void eliminar(Long id) {
        alumnoForCursoRepository.deleteById(id);
    }

    @Override
    public List<AlumnoForCursoDto> findByCodCurso(Long codCurso) {
        var cursosInscritos = alumnoForCursoRepository.findByIdAlumno(codCurso);
        List<AlumnoForCursoDto> cursosInscritosDto = new ArrayList<>();
        if (cursosInscritos != null) {
            cursosInscritos.forEach(curso -> cursosInscritosDto.add(AlumnoForCursoDto.builder()
                    .codAlumnoPorCurso(curso.getIdAlumnoCurso())
                    .alumno(AlumnoDto.builder().codAlumno(curso.getIdAlumno()).build())
                    .curso(CursoDto.builder().id(curso.getIdCurso()).build())
                    .build()));
        }

        return cursosInscritosDto;
    }

    @Override
    public List<AlumnoForCursoDto> findByCodAlumno(Long codAlumno) {

        var cursosInscritos = alumnoForCursoRepository.findByIdAlumno(codAlumno);
        List<AlumnoForCursoDto> cursosInscritosDto = new ArrayList<>();
        if (cursosInscritos != null) {
            cursosInscritos.forEach(curso -> cursosInscritosDto.add(AlumnoForCursoDto.builder()
                    .codAlumnoPorCurso(curso.getIdAlumnoCurso())
                    .alumno(AlumnoDto.builder().codAlumno(curso.getIdAlumno()).build())
                    .curso(CursoDto.builder().id(curso.getIdCurso()).build())
                    .build()));
        }

        return cursosInscritosDto;
    }

}
