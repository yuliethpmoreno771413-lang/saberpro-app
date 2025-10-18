package com.saberpro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "resultados")
public class Resultado {

    @Id
    private String id;
    private String numeroRegistro;
    private String estudianteId;
    private double puntajeTotal;
    private LocalDate fecha = LocalDate.now();
    private List<Materia> materias;

    public Resultado() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNumeroRegistro() { return numeroRegistro; }
    public void setNumeroRegistro(String numeroRegistro) { this.numeroRegistro = numeroRegistro; }

    public String getEstudianteId() { return estudianteId; }
    public void setEstudianteId(String estudianteId) { this.estudianteId = estudianteId; }

    public double getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(double puntajeTotal) { this.puntajeTotal = puntajeTotal; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public List<Materia> getMaterias() { return materias; }
    public void setMaterias(List<Materia> materias) { this.materias = materias; }
}
