package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.ProfesorDao;
import com.proyecto.daw.proyectodaw.domain.Profesor;
import com.proyecto.daw.proyectodaw.dto.ProfesorDto;
import com.proyecto.daw.proyectodaw.util.UtilService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorDao profesorDao;

    @Autowired
    private UtilService utilService;

    @Override
    public ProfesorDto obtenerProfesorPorNombre(String nombre) {
        var resp = profesorDao.findByNombres(nombre);
        if (resp.isPresent())
            return retornarProfesorDto(resp.get());
        throw new UnsupportedOperationException("No se pudo obtener el profesor cuando se busco por nombres");
    }

    @Override
    public ProfesorDto obtenerProfesorPorDni(String dni) {

        var resp = profesorDao.findByDni(dni);
        if (resp.isPresent())
            return retornarProfesorDto(resp.get());
        throw new UnsupportedOperationException("No se pudo obtener el profesor");
    }

    @Override
    public List<ProfesorDto> obtenerListaProfesores() {

        var resp = profesorDao.findAll();
        List<ProfesorDto> listaProfesores = new ArrayList<>();
        for (Profesor profesor : resp) {
            listaProfesores.add(retornarProfesorDto(profesor));
        }
        return listaProfesores;

    }

    @Override
    public ProfesorDto agregarProfesor(ProfesorDto profesor) {

        var resp = profesorDao.save(Profesor.builder()
                .nombres(profesor.getNombre())
                .apellidos(profesor.getApellidos())
                .dni(profesor.getDni())
                .build());
        if (resp.getIdProfesor() != null) {
            return retornarProfesorDto(resp);
        }

        throw new UnsupportedOperationException("No se pudo agregar el profesor");
    }

    @Override
    public ProfesorDto editarProfesor(ProfesorDto profesor) {

        var resp = profesorDao.save(Profesor.builder()
                .idProfesor(profesor.getId())
                .nombres(profesor.getNombre())
                .apellidos(profesor.getApellidos())
                .dni(profesor.getDni())
                .build());
        if (resp.getIdProfesor() != null) {
            return retornarProfesorDto(resp);
        }
        throw new UnsupportedOperationException("No se pudo editar el profesor");

    }

    @Override
    public void eliminarProfesor(ProfesorDto profesor) {

        profesorDao.delete(
                profesorDao.findById(
                        profesor.getId())
                        .orElseThrow(
                                () -> new UnsupportedOperationException("No se pudo eliminar el profesor")));

    }

    @Override
    public ProfesorDto obtenerProfesorPorId(Long id) {
        var valor = profesorDao.findById(id);
        if (valor.isPresent() && valor.get().getIdProfesor() != null) {
            return retornarProfesorDto(valor.get());
        }
        return ProfesorDto.builder().build();
    }

    public ProfesorDto retornarProfesorDto(Profesor profesor) {
        return ProfesorDto.builder()
                .id(profesor.getIdProfesor())
                .nombre(profesor.getNombres())
                .apellidos(profesor.getApellidos())
                .dni(profesor.getDni())
                .curso(utilService.listarCursosPorProfesor(profesor.getIdProfesor()))
                .build();

    }

}
