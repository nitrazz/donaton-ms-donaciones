package com.gestionDonaton.gestion_donaciones.producer;

import com.gestionDonaton.gestion_donaciones.dto.DonacionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DonacionProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchanges.donaciones}")
    private String exchangeDonaciones;

    @Value("${app.rabbitmq.routing-keys.donacion-creada}")
    private String routingKeyDonacion;

    public DonacionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publica el evento de donación creada en el Exchange de RabbitMQ en AWS.
     * @param evento Instancia del record DonacionEvent
     */
    public void publicarDonacionCreada(DonacionEvent evento) {
        System.out.println("Publicando evento a AWS RabbitMQ -> Exchange: "
                + exchangeDonaciones + " | RoutingKey: " + routingKeyDonacion);
        System.out.println("Payload: " + evento);

        rabbitTemplate.convertAndSend(exchangeDonaciones, routingKeyDonacion, evento);
    }
}
