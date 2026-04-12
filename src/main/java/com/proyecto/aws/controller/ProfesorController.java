package com.proyecto.aws.controller;

import com.proyecto.aws.model.Profesor;
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
@RequestMapping("/profesores")
public class ProfesorController {

    private final List<Profesor> profesores = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<Profesor>> getProfesores() {
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable Long id) {
        Optional<Profesor> profesor = profesores.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (profesor.isPresent()) {
            return ResponseEntity.ok(profesor.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Profesor> createProfesor(@Valid @RequestBody Profesor profesor) {
        profesor.setId(counter.incrementAndGet());
        profesores.add(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @Valid @RequestBody Profesor profesorDetails) {
        Optional<Profesor> optionalProfesor = profesores.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            profesor.setNumeroEmpleado(profesorDetails.getNumeroEmpleado());
            profesor.setNombres(profesorDetails.getNombres());
            profesor.setApellidos(profesorDetails.getApellidos());
            profesor.setHorasClase(profesorDetails.getHorasClase());
            return ResponseEntity.ok(profesor);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        boolean removed = profesores.removeIf(p -> p.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado");
        }
    }
}
