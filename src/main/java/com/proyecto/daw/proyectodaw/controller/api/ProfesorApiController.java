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

import com.proyecto.daw.proyectodaw.dto.ProfesorDto;
import com.proyecto.daw.proyectodaw.service.ProfesorService;

@RestController
@RequestMapping("/api-profesor")
public class ProfesorApiController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCursos() {

        return ResponseEntity.ok(profesorService.obtenerListaProfesores());

    }

    @GetMapping("/profesor")
    public ResponseEntity<Object> getProfesorForPatrametter(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "dni", required = false) String dni) {

        if (id != null) {
            return ResponseEntity.ok(profesorService.obtenerProfesorPorId(id));
        } else if (nombre != null) {
            return ResponseEntity.ok(profesorService.obtenerProfesorPorNombre(nombre));
        } else if (dni != null) {
            return ResponseEntity.ok(profesorService.obtenerProfesorPorDni(dni));
        } else {
            return ResponseEntity.badRequest().body("No se ha encontrado el profesor");
        }

    }

    @PostMapping("/profesor")
    public ResponseEntity<Object> agregarProfesor(@RequestBody ProfesorDto profesorDto) {
        var resp = profesorService.agregarProfesor(profesorDto);

        if (resp != null) {
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.badRequest().body("No se ha podido agregar el profesor");
        }
    }

    @PutMapping("/profesor")
    public ResponseEntity<Object> editarProfesor(@RequestBody ProfesorDto profesorDto) {

        var resp = profesorService.editarProfesor(profesorDto);

        if (resp != null) {
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.badRequest().body("No se ha podido editar el profesor");
        }
    }

    @DeleteMapping("/profesor")
    public ResponseEntity<Object> eliminarProfesor(@RequestParam(name = "id") Long id) {

        profesorService.eliminarProfesor(ProfesorDto.builder().id(id).build());
        Map<String, String> response = new HashMap<>();
        response.put("codigo", "200");
        response.put("mensaje", "Profesor eliminado correctamente");
        return ResponseEntity.ok(response);

    }

}
