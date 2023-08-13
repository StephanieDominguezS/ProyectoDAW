package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.AlumnoDto;

public interface AlumnoService {
    
    public AlumnoDto obtenerAlumnoPorNombre(String nombre);

    public AlumnoDto obtenerAlumnoPorDni(String dni);

    public List<AlumnoDto> obtenerListaAlumnos();

    public AlumnoDto agregarAlumno(AlumnoDto alumno);

    public AlumnoDto editarAlumno(AlumnoDto alumno);

    public void eliminarAlumno(AlumnoDto alumno);

    
}
