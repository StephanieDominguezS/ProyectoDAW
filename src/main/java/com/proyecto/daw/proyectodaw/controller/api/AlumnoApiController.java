package com.proyecto.daw.proyectodaw.controller.api;

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

import com.proyecto.daw.proyectodaw.dto.AlumnoDto;
import com.proyecto.daw.proyectodaw.service.AlumnoService;

@RestController
@RequestMapping("/api-alumno")
public class AlumnoApiController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumno/all")
    public ResponseEntity<Object> getAllUsers() {

        var alumnos = alumnoService.obtenerListaAlumnos();
        if (alumnos == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(alumnos);
        }
    }

    @GetMapping("/alumno")
    public ResponseEntity<Object> getAlumnoById(
        @RequestParam(name = "id",required = false) Integer id,
        @RequestParam(name = "nombre",required = false) String nombre) {

        AlumnoDto alumnos = null ;
        if(id!=null) alumnos = alumnoService.obtenerAlumnoPorId(id);
        else if(nombre!=null) alumnos = alumnoService.obtenerAlumnoPorNombre(nombre);

        if (alumnos == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(alumnos);
        }
    }

    @PostMapping("/alumno")
    public ResponseEntity<Object> saveAlumno(@RequestBody AlumnoDto alumnoDto) {

        var alumno = alumnoService.agregarAlumno(alumnoDto);

        if (alumno == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(alumno);
        }
    }

    @PutMapping("/alumno")
    public ResponseEntity<Object> updateAlumno(@RequestBody AlumnoDto alumnoDto) {

        var alumno = alumnoService.editarAlumno(alumnoDto);

        if (alumno == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(alumno);
        }
    }

    @DeleteMapping("/alumno")
    public ResponseEntity<Object> deleteAlumno(@RequestParam(name = "id",required = false) Long id) {

            alumnoService.eliminarAlumno(AlumnoDto.builder().codAlumno(id).build());

            return ResponseEntity.ok().build();
        
    }

}
