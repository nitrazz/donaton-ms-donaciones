package com.gestionDonaton.gestion_donaciones.service;

import com.gestionDonaton.gestion_donaciones.dto.DonacionEvent;
import com.gestionDonaton.gestion_donaciones.dto.DonacionRequestDTO;
import com.gestionDonaton.gestion_donaciones.dto.DonacionResponseDTO;
import com.gestionDonaton.gestion_donaciones.factory.DonacionFactory;
import com.gestionDonaton.gestion_donaciones.model.Donacion;
import com.gestionDonaton.gestion_donaciones.model.DonacionEmpresarial;
import com.gestionDonaton.gestion_donaciones.producer.DonacionProducer;
import com.gestionDonaton.gestion_donaciones.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonacionService {

    @Autowired
    private DonacionRepository repository;

    @Autowired
    private DonacionProducer donacionProducer;

    public DonacionResponseDTO registrarDonacion(DonacionRequestDTO request) {
        Donacion donacion = DonacionFactory.crearDonacion(request.getTipo());

        donacion.setMonto(request.getMonto());
        donacion.setDonanteNombre(request.getNombre());
        donacion.setNombreObjeto(request.getObjeto());
        donacion.setEstado("PENDIENTE");

        if (donacion instanceof DonacionEmpresarial emp) {
            emp.setRutEmpresa(request.getRut());
            emp.setCertificadoImpuestos(request.getCertificado());
        }

        Donacion guardada = repository.save(donacion);

        // ── Publicar evento a AWS RabbitMQ ────────────────────────────────────
        try {
            DonacionEvent evento = new DonacionEvent(
                    guardada.getId(),
                    guardada.getDonanteNombre(),
                    guardada.getMonto(),
                    guardada.getEstado()
            );
            donacionProducer.publicarDonacionCreada(evento);
        } catch (Exception e) {
            System.err.println("⚠️ No se pudo enviar el evento a RabbitMQ: " + e.getMessage());
        }

        return toDTO(guardada);
    }

    public List<DonacionResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<DonacionResponseDTO> buscarPorPalabra(String palabra) {
        return repository.findByNombreObjetoContainingIgnoreCase(palabra)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DonacionResponseDTO actualizarEstadoCompletado(Long id) {
        Optional<Donacion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Donacion donacion = optional.get();
            donacion.setEstado("COMPLETADA");
            Donacion guardada = repository.save(donacion);

            try {
                DonacionEvent evento = new DonacionEvent(
                        guardada.getId(),
                        guardada.getDonanteNombre(),
                        guardada.getMonto(),
                        guardada.getEstado()
                );
                donacionProducer.publicarDonacionCreada(evento);
            } catch (Exception e) {
                System.err.println("⚠️ Error enviando evento a RabbitMQ: " + e.getMessage());
            }

            return toDTO(guardada);
        }
        return null;
    }

    // ── Mapper entidad → DTO (Constructor directo sin depender de Builder) ───
    private DonacionResponseDTO toDTO(Donacion d) {
        String tipo = (d instanceof DonacionEmpresarial) ? "EMPRESARIAL" : "INDIVIDUAL";
        String rut = (d instanceof DonacionEmpresarial emp) ? emp.getRutEmpresa() : null;
        String certificado = (d instanceof DonacionEmpresarial emp) ? emp.getCertificadoImpuestos() : null;

        return new DonacionResponseDTO(
                d.getId(),
                tipo,
                d.getMonto(),
                d.getDonanteNombre(),
                d.getNombreObjeto(),
                d.getEstado(),
                rut,
                certificado
        );
    }
}