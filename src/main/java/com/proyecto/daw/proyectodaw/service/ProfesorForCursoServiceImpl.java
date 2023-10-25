package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.ProfesorForCursoDao;
import com.proyecto.daw.proyectodaw.domain.ProfesorForCurso;
import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.dto.ProfesorDto;
import com.proyecto.daw.proyectodaw.dto.ProfesorForCursoDto;

@Service
public class ProfesorForCursoServiceImpl implements ProfesorForCursoService {

    @Autowired
    private ProfesorForCursoDao profesorForCursoDao;

    @Override
    public ProfesorForCursoDto findById(Long id) {
        var resp = profesorForCursoDao.findById(id);

        if (resp.isPresent()) {
            return responderProfesorForCursoDto(resp.get());
        } else {
            return ProfesorForCursoDto.builder().build();
        }
    }

    @Override
    public List<ProfesorForCursoDto> findByCurso(Long idCurso) {
        var resp = profesorForCursoDao.findAllByIdCurso(idCurso);
        List<ProfesorForCursoDto> lista = new ArrayList<>();
        for (ProfesorForCurso p : resp) {
            lista.add(responderProfesorForCursoDto(p));
        }

        return lista;
    }

    @Override
    public List<ProfesorForCursoDto> findByProfesor(Long idProfesor) {

        var resp = profesorForCursoDao.findAllByIdProfesor(idProfesor);
        List<ProfesorForCursoDto> lista = new ArrayList<>();
        for (ProfesorForCurso p : resp) {
            lista.add(responderProfesorForCursoDto(p));
        }

        return lista;
    }

    @Override
    public ProfesorForCursoDto save(ProfesorForCursoDto profesorForCursoDto) {

        var resp = profesorForCursoDao.save(ProfesorForCurso.builder()
                .idCurso(profesorForCursoDto.getCurso().getId())
                .idProfesor(profesorForCursoDto.getProfesor().getId())
                .build());

        if (resp.getIdProForCurso() != null) {
            return responderProfesorForCursoDto(resp);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {

        profesorForCursoDao.deleteById(
                profesorForCursoDao.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)).getIdProForCurso());

    }

    @Override
    public ProfesorForCursoDto edit(ProfesorForCursoDto profesorForCursoDto) {

        var resp = profesorForCursoDao.save(ProfesorForCurso.builder()
                .idProForCurso(profesorForCursoDto.getCodProfesorPorCuro())
                .idCurso(profesorForCursoDto.getCurso().getId())
                .idProfesor(profesorForCursoDto.getProfesor().getId())
                .build());

        if (resp.getIdProForCurso() != null) {
            return responderProfesorForCursoDto(resp);
        } else {
            return null;
        }
    }

    @Override
    public List<ProfesorForCursoDto> findAll() {
        var resp = profesorForCursoDao.findAll();
        List<ProfesorForCursoDto> lista = new ArrayList<>();
        for (ProfesorForCurso p : resp) {
            lista.add(responderProfesorForCursoDto(p));
        }
        return lista;
    }

    public ProfesorForCursoDto responderProfesorForCursoDto(ProfesorForCurso resp) {

        return ProfesorForCursoDto.builder()
                .codProfesorPorCuro(resp.getIdProForCurso())
                .curso(CursoDto.builder().id(resp.getIdCurso()).build())
                .profesor(ProfesorDto.builder().id(resp.getIdProfesor()).build())
                .build();

    }

}
