package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.CursoDao;
import com.proyecto.daw.proyectodaw.domain.Curso;
import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.handler.CursoException;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao cursoDao;

    @Override
    public CursoDto buscarPorCodCurso(Long id) {

        var curso = cursoDao.findByIdCurso(id);

        if (curso.isPresent()) {
            return retortanarCursoDto(curso.get());
        }

        return CursoDto.builder().build();
    }

    @Override
    public CursoDto guardar(CursoDto curso) {

        var registro = cursoDao.save(Curso.builder()
                .nombre(curso.getNombre())
                .descripcion(curso.getDescripcion())
                .horario(curso.getHorario())
                .build());

        if (registro.getIdCurso() != null) {
            return retortanarCursoDto(registro);
        }
        throw new CursoException.CursoNoGuardadoException("No se ha podido guardar el curso");
    }

    @Override
    public CursoDto editar(CursoDto curso) {
        var resp = cursoDao.save(Curso.builder()
                .idCurso(curso.getId())
                .nombre(curso.getNombre())
                .descripcion(curso.getDescripcion())
                .horario(curso.getHorario())
                .build());
        return retortanarCursoDto(resp);

    }

    @Override
    public void eliminar(Long id) {

        cursoDao.delete(cursoDao.findById(id).orElseThrow(() -> new CursoException("No se ha encontrado el curso")));
    }

    @Override
    public CursoDto buscarPorNombre(String nombre) {

        var res = cursoDao.findByNombre(nombre);
        if (res.isPresent()) {
            return retortanarCursoDto(res.get());
        }
        throw new CursoException("No se ha encontrado el curso");
    }

    @Override
    public List<CursoDto> listarCursos() {
        var reg = cursoDao.findAll();
        List<CursoDto> lista = new ArrayList<>();
        for (Curso curso : reg) {
            lista.add(retortanarCursoDto(curso));
        }
        return lista;
    }

    public CursoDto retortanarCursoDto(Curso registro) {
        return CursoDto.builder()
                .id(registro.getIdCurso())
                .nombre(registro.getNombre())
                .descripcion(registro.getDescripcion())
                .horario(registro.getHorario())
                .build();
    }

}
