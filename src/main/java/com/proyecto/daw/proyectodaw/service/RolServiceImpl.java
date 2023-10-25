package com.proyecto.daw.proyectodaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.proyectodaw.dao.RolDao;
import com.proyecto.daw.proyectodaw.domain.Rol;
import com.proyecto.daw.proyectodaw.dto.RolDto;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDao rolDao;

    @Override
    public RolDto findRolByDescription(String description) {

        var resp = rolDao.findByRolName(description);
        if (resp.isPresent()) {
            return createRolDto(resp.get());
        }
        throw new UnsupportedOperationException("Unimplemented method 'findRolByDescription'");
    }

    @Override
    public RolDto findRolById(Long id) {
        var resp = rolDao.findById(id);
        if (resp.isPresent()) {
            return createRolDto(resp.get());
        }
        throw new UnsupportedOperationException("Unimplemented method 'Busqueda por id'");
    }

    @Override
    public RolDto save(RolDto rolDto) {
        var resp = rolDao.save(Rol.builder().rolName(rolDto.getDescripcion()).build());
        if (resp.getIdRol() != null) {
            return createRolDto(resp);
        }
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteById(Long id) {

        rolDao.delete(
                rolDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));

    }

    @Override
    public RolDto update(RolDto rolDto) {

        var resp = rolDao.save(
                Rol.builder()
                        .idRol(rolDto.getId())
                        .rolName(rolDto.getDescripcion())
                        .build());
        if (resp.getIdRol() != null) {
            return createRolDto(resp);
        }

        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<RolDto> findAll() {

        var resp = rolDao.findAll();
        List<RolDto> roles = new ArrayList<>();
        for (Rol rol : resp) {
            roles.add(createRolDto(rol));
        }
        return roles;

    }

    public RolDto createRolDto(Rol rol) {
        return RolDto.builder().id(rol.getIdRol()).descripcion(rol.getRolName()).build();
    }

}
