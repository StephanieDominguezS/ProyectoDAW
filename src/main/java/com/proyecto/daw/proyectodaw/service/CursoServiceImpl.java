package com.proyecto.daw.proyectodaw.service;

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
            return CursoDto.builder()
                    .id(id)
                    .nombre(curso.get().getNombre())
                    .descripcion(curso.get().getDescripcion())
                    .horario(curso.get().getHorario())
                    .build();
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
            return CursoDto.builder()
                    .id(registro.getIdCurso())
                    .nombre(registro.getNombre())
                    .descripcion(registro.getDescripcion())
                    .horario(registro.getHorario())
                    .build();
        }
        throw new CursoException.CursoNoGuardadoException("No se ha podido guardar el curso");
    }

    @Override
    public void editar(CursoDto curso) {
        cursoDao.save(Curso.builder()
                .idCurso(curso.getId())
                .nombre(curso.getNombre())
                .descripcion(curso.getDescripcion())
                .horario(curso.getHorario())
                .build());

        throw new CursoException.CursoNoActualizadoException("No se ha podido Actualizar el curso");
    }

    @Override
    public void eliminar(Long id) {
        cursoDao.deleteById(id);
        throw new CursoException("No se ha podido eliminar el curso");
    }

}
