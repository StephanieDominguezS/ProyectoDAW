package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.NotaDto;

public interface NotaService {
    
    public List<NotaDto> obtenerNotaPorNombre(String nombre);

    public List<NotaDto> obtenerNotaPorDni(String dni);

    public List<NotaDto> obtenerListaNotas();

    public NotaDto agregarNota(NotaDto nota);

    public NotaDto editarNota(NotaDto nota);

    public void eliminarNota(NotaDto nota);
    
}
