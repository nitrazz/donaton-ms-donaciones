package com.gestionDonaton.gestion_donaciones.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPRESARIAL")
public class DonacionEmpresarial extends Donacion {

    private String rutEmpresa;
    private String certificadoImpuestos;

    public DonacionEmpresarial() {
        super();
    }

    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    public String getCertificadoImpuestos() {
        return certificadoImpuestos;
    }

    public void setCertificadoImpuestos(String certificadoImpuestos) {
        this.certificadoImpuestos = certificadoImpuestos;
    }
}