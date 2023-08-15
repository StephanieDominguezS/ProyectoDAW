package com.proyecto.daw.proyectodaw.service;

import java.util.List;

import com.proyecto.daw.proyectodaw.dto.RolDto;

public interface RolService {
    
        public List<RolDto> findAll();

        public RolDto findRolByDescription(String description);

        public RolDto findRolById(Long id);

        public RolDto save(RolDto rolDto);

        public void deleteById(Long id);

        public RolDto update(RolDto rolDto);

}
