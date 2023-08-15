package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.ProfesorDto;

public interface ProfesorService {
    
    public ProfesorDto obtenerProfesorPorNombre(String nombre);

    public ProfesorDto obtenerProfesorPorDni(String dni);

    public ProfesorDto obtenerProfesorPorId(Long id);

    public List<ProfesorDto> obtenerListaProfesores();

    public ProfesorDto agregarProfesor(ProfesorDto profesor);

    public ProfesorDto editarProfesor(ProfesorDto profesor);

    public void eliminarProfesor(ProfesorDto profesor);
    
}
