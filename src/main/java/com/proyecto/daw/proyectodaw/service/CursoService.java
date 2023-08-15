package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.CursoDto;

public interface CursoService {

    public CursoDto buscarPorCodCurso(Long id);

    public CursoDto guardar(CursoDto curso);

    public CursoDto editar(CursoDto curso);

    public void eliminar(Long id);

    public CursoDto buscarPorNombre(String nombre);

    public List<CursoDto> listarCursos();
}
