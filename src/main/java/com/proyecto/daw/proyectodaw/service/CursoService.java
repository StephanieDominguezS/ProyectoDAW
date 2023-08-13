package com.proyecto.daw.proyectodaw.service;

import com.proyecto.daw.proyectodaw.dto.CursoDto;

public interface CursoService {
    
    public CursoDto buscarPorCodCurso(Long id);

    public CursoDto guardar(CursoDto curso);

    public void editar(CursoDto curso);

    public void eliminar(Long id);
}
