package com.umg.notass.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asignacion {
    private int id;
    private int estudianteId;
    private int cursoId;
    private String fechaAsignacion;
    private boolean estado;

    public Asignacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}