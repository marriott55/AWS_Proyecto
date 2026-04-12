package com.proyecto.aws.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class Alumno {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    private String apellidos;

    @NotBlank(message = "La matrícula no puede estar vacía")
    private String matricula;

    @NotNull(message = "El promedio no puede ser nulo")
    @Min(value = 0, message = "El promedio no puede ser negativo")
    private Double promedio;

    public Alumno() {
    }

    public Alumno(Long id, String nombres, String apellidos, String matricula, Double promedio) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.promedio = promedio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Double getPromedio() { return promedio; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }
}
