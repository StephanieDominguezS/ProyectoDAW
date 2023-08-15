package com.proyecto.daw.proyectodaw.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.daw.proyectodaw.dto.ProfesorForCursoDto;
import com.proyecto.daw.proyectodaw.service.ProfesorForCursoService;

@RestController
@RequestMapping("/api-profesor-curso")
public class ProfesorCursoApiController {

    @Autowired
    private ProfesorForCursoService profesorForCursoService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(profesorForCursoService.findAll());
    }

    @GetMapping("/profesor/all")
    public ResponseEntity<Object> getAllProfesores(
            @RequestParam(name = "id", required = true) Long id) {
        return ResponseEntity.ok(profesorForCursoService.findByProfesor(id));
    }

    @GetMapping("/curso/all")
    public ResponseEntity<Object> getAllCursos(
            @RequestParam(name = "id", required = true) Long id) {
        return ResponseEntity.ok(profesorForCursoService.findByCurso(id));
    }

    @GetMapping("/id/all")
    public ResponseEntity<Object> getById(
            @RequestParam(name = "id", required = true) Long id) {
        return ResponseEntity.ok(profesorForCursoService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ProfesorForCursoDto profesorForCursoDto) {
        return ResponseEntity.ok(profesorForCursoService.save(profesorForCursoDto));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ProfesorForCursoDto profesorForCursoDto) {
        return ResponseEntity.ok(profesorForCursoService.edit(profesorForCursoDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam(name = "id", required = true) Long id) {
        profesorForCursoService.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("codigo", "200");
        response.put("mensaje", "Profesor eliminado correctamente");
        return ResponseEntity.ok(response);
    }

}
