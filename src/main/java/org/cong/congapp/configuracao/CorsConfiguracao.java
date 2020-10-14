package org.cong.congapp.configuracao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguracao implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(CorsConfiguracao.class);
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200") //host do front
                //.allowedOrigins("http://congap.herokuapp.com/") //host do front
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

        logger.info("Configuracao CORS tem sido registrado");
    }
}
