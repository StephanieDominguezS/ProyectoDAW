package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.ProfesorForCursoDto;

public interface ProfesorForCursoService {

    public List<ProfesorForCursoDto> findAll();

    public ProfesorForCursoDto findById(Long id);

    public List<ProfesorForCursoDto> findByCurso(Long idCurso);

    public List<ProfesorForCursoDto> findByProfesor(Long idProfesor);

    public ProfesorForCursoDto save(ProfesorForCursoDto profesorForCursoDto);

    public void deleteById(Long id);

    public ProfesorForCursoDto edit(ProfesorForCursoDto profesorForCursoDto);
        
}
