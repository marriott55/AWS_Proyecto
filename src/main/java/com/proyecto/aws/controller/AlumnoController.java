package com.proyecto.aws.controller;

import com.proyecto.aws.model.Alumno;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final List<Alumno> alumnos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos() {
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnos.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Alumno> createAlumno(@Valid @RequestBody Alumno alumno) {
        alumno.setId(counter.incrementAndGet());
        alumnos.add(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @Valid @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> optionalAlumno = alumnos.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();
            alumno.setNombres(alumnoDetails.getNombres());
            alumno.setApellidos(alumnoDetails.getApellidos());
            alumno.setMatricula(alumnoDetails.getMatricula());
            alumno.setPromedio(alumnoDetails.getPromedio());
            return ResponseEntity.ok(alumno);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        boolean removed = alumnos.removeIf(a -> a.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
    }
}
