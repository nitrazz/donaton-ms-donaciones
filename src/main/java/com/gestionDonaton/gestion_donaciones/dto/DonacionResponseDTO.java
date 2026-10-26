package com.gestionDonaton.gestion_donaciones.dto;


public class DonacionResponseDTO {
    private Long id;
    private String tipo;          // "INDIVIDUAL" o "EMPRESARIAL"
    private Double monto;
    private String donanteNombre;
    private String nombreObjeto;
    private String estado;        // "PENDIENTE" o "COMPLETADA"
    private String rutEmpresa;    // null si es INDIVIDUAL
    private String certificadoImpuestos; // null si es INDIVIDUAL

    public DonacionResponseDTO() {}

    public DonacionResponseDTO(Long id, String tipo, Double monto, String donanteNombre,
                               String nombreObjeto, String estado, String rutEmpresa, String certificadoImpuestos) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.donanteNombre = donanteNombre;
        this.nombreObjeto = nombreObjeto;
        this.estado = estado;
        this.rutEmpresa = rutEmpresa;
        this.certificadoImpuestos = certificadoImpuestos;
    }

    // Getters para que Jackson pueda serializar a JSON hacia el BFF / Frontend
    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public Double getMonto() { return monto; }
    public String getDonanteNombre() { return donanteNombre; }
    public String getNombreObjeto() { return nombreObjeto; }
    public String getEstado() { return estado; }
    public String getRutEmpresa() { return rutEmpresa; }
    public String getCertificadoImpuestos() { return certificadoImpuestos; }
}