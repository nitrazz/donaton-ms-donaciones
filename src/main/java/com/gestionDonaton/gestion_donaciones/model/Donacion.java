package com.gestionDonaton.gestion_donaciones.model;

import jakarta.persistence.*;


@Entity
@Table(name = "donaciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_donacion")
public abstract class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto", nullable = true, unique = false)
    private Double monto;

    private String donanteNombre;
    private String nombreObjeto;
    private String estado = "PENDIENTE";

    public Donacion() {}

    public Donacion(Long id, Double monto, String donanteNombre, String nombreObjeto, String estado) {
        this.id = id;
        this.monto = monto;
        this.donanteNombre = donanteNombre;
        this.nombreObjeto = nombreObjeto;
        this.estado = estado;
    }

    // ── Getters y Setters explícitos ──────────────────────────────────────────
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDonanteNombre() {
        return donanteNombre;
    }

    public void setDonanteNombre(String donanteNombre) {
        this.donanteNombre = donanteNombre;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
