package utez.edu.mx.crochetifyBack.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registrar el módulo para soporte de fechas y horas de Java 8
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}