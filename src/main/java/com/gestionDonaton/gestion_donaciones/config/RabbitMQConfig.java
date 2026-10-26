package com.gestionDonaton.gestion_donaciones.config;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * Utiliza la nueva clase JacksonJsonMessageConverter compatible con Spring Boot 3.4+,
     * Spring AMQP 4.0+ y Java 21 LTS.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}