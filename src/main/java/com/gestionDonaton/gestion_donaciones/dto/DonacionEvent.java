package com.gestionDonaton.gestion_donaciones.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import java.io.Serializable;

public record DonacionEvent(
        Long idDonacion,
        String donante,
        Double monto,
        String estado
) implements Serializable {}