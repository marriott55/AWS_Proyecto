package com.proyecto.aws.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class Profesor {
    private Long id;

    @NotBlank(message = "El número de empleado no puede estar vacío")
    private String numeroEmpleado;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    private String apellidos;

    @NotNull(message = "Las horas clase no pueden ser nulas")
    @Min(value = 0, message = "Las horas clase no pueden ser negativas")
    private Integer horasClase;

    public Profesor() {}

    public Profesor(Long id, String numeroEmpleado, String nombres, String apellidos, Integer horasClase) {
        this.id = id;
        this.numeroEmpleado = numeroEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.horasClase = horasClase;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Integer getHorasClase() { return horasClase; }
    public void setHorasClase(Integer horasClase) { this.horasClase = horasClase; }
}
