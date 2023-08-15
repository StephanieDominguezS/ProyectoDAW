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

import com.proyecto.daw.proyectodaw.dto.AlumnoForCursoDto;
import com.proyecto.daw.proyectodaw.service.AlumnoForCursoService;

@RestController
@RequestMapping("/api-alumno-curso")
public class AlumnoCursoApiControlle {

    @Autowired
    private AlumnoForCursoService alumnoForCursoService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok().body(alumnoForCursoService.findAll());
    }

    @GetMapping("/all/curso")
    public ResponseEntity<Object> getAllByCurso(@RequestParam(name = "id", required = false) Long id) {
        return ResponseEntity.ok().body(alumnoForCursoService.findByCodCurso(id));
    }

    @GetMapping("/all/alumno")
    public ResponseEntity<Object> getAllByAlumno(@RequestParam(name = "id", required = false) Long id) {
        return ResponseEntity.ok().body(alumnoForCursoService.findByCodAlumno(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addAlumnoForCurso(@RequestBody AlumnoForCursoDto alumnoForCursoDto) {

        return ResponseEntity.ok().body(alumnoForCursoService.guardar(alumnoForCursoDto));

    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAlumnoForCurso(@RequestBody AlumnoForCursoDto alumnoForCursoDto) {

        return ResponseEntity.ok().body(alumnoForCursoService.editar(alumnoForCursoDto));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAlumnoForCurso(@RequestParam(name = "id", required = false) Long id) {

        alumnoForCursoService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("codigo", "200");
        response.put("mensaje", "Profesor eliminado correctamente");
        return ResponseEntity.ok().body(response);

    }

}
