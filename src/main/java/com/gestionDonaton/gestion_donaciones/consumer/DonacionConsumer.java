package com.gestionDonaton.gestion_donaciones.consumer;

import com.gestionDonaton.gestion_donaciones.dto.DonacionEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DonacionConsumer {

    @RabbitListener(queues = "${app.rabbitmq.queues.donaciones}")
    public void procesarDonacion(DonacionEvent evento) {
        System.out.println("📥 [MS-Donaciones / JDK 21] Mensaje recibido desde AWS: " + evento);

        // Acceso nativo de JDK 21 Record: evento.estado()
        if ("ERROR".equalsIgnoreCase(evento.estado())) {
            System.err.println("❌ Fallo intencional detectado -> Redirigiendo a dlq.donaciones...");
            throw new RuntimeException("Error simulado: Mensaje enviado a la DLQ");
        }

        System.out.println("✅ Evento procesado correctamente.");
    }
}
