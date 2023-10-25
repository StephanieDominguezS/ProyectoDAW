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

import com.proyecto.daw.proyectodaw.dto.CursoDto;
import com.proyecto.daw.proyectodaw.service.CursoService;

@RestController
@RequestMapping("/api-curso")
public class CursoApiController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCursos() {

        var cursos = cursoService.listarCursos();

        if (cursos == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cursos);
        }

    }

    @GetMapping("/curso")
    public ResponseEntity<Object> getCursoByParametter(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "nombre", required = false) String nombre) {

        CursoDto curso = null;

        if (id != null) {
            curso = cursoService.buscarPorCodCurso(id);
        } else if (nombre != null) {
            curso = cursoService.buscarPorNombre(nombre);
        }

        return ResponseEntity.ok(curso);

    }

    @PostMapping("/curso")
    public ResponseEntity<Object> postCurso(@RequestBody CursoDto curso) {

        var resp = cursoService.guardar(curso);

        if (resp == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(resp);

    }

    @PutMapping("/curso")
    public ResponseEntity<Object> putCurso(@RequestBody CursoDto curso) {

        var resp = cursoService.editar(curso);

        if (resp == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(resp);

    }

    @DeleteMapping("/curso")
    public ResponseEntity<Object> deleteCurso(@RequestParam(name = "id") Long id) {

        cursoService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("codigo", "200");
        response.put("mensaje", "Profesor eliminado correctamente");
        return ResponseEntity.ok().body(response);

    }

}
