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

import com.proyecto.daw.proyectodaw.dto.RolDto;
import com.proyecto.daw.proyectodaw.service.RolService;

@RestController
@RequestMapping("/api-rol")
public class RolApiController {

    @Autowired
    private RolService rolService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(rolService.findAll());
    }

    @GetMapping("/all/rol")
    public ResponseEntity<Object> getAllRolByParameter(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "description", required = false) String description) {

        if (id != null) {
            return ResponseEntity.ok(rolService.findRolById(id));
        } else if (!description.isEmpty()) {
            return ResponseEntity.ok(rolService.findRolByDescription(description));
        } else {
            return ResponseEntity.ok(rolService.findAll());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addRol(@RequestBody RolDto rolDto) {
        return ResponseEntity.ok(rolService.save(rolDto));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateRol(@RequestBody RolDto rolDto) {
        return ResponseEntity.ok(rolService.update(rolDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRol(@RequestParam(name = "id") Long id) {
        rolService.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("codigo", "200");
        response.put("mensaje", "Profesor eliminado correctamente");
        return ResponseEntity.ok(response);
    }

}
