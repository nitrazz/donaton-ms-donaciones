package com.gestionDonaton.gestion_donaciones.dto;

import lombok.Data;

public class DonacionRequestDTO {

    private String tipo;            // "INDIVIDUAL" o "EMPRESARIAL"
    private Double monto;
    private String nombre;          // nombre del donante
    private String objeto;          // nombre del objeto/recurso donado
    private String rut;             // solo para EMPRESARIAL (opcional)
    private String certificado;     // solo para EMPRESARIAL (opcional)

    public DonacionRequestDTO() {}

    public DonacionRequestDTO(String tipo, Double monto, String nombre, String objeto, String rut, String certificado) {
        this.tipo = tipo;
        this.monto = monto;
        this.nombre = nombre;
        this.objeto = objeto;
        this.rut = rut;
        this.certificado = certificado;
    }

    // ── Getters y Setters explícitos ──────────────────────────────────────────
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }
}