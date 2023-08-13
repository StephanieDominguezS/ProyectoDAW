package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.AlumnoForCursoDto;

public interface AlumnoForCursoService {

    public AlumnoForCursoDto findById(Long id);

    public AlumnoForCursoDto guardar(AlumnoForCursoDto alumnoForCurso);

    public void editar(AlumnoForCursoDto alumnoForCurso);

    public void eliminar(Long id);

    public List<AlumnoForCursoDto> findByCodCurso(Long codCurso);

    public List<AlumnoForCursoDto> findByCodAlumno(Long codAlumno);


}
