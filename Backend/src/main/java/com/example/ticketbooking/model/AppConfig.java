package com.example.ticketbooking.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * This configuration class enables Cross-Origin Resource Sharing (CORS) for the application, allowing requests from the specified origin.
 */
@Configuration
public class AppConfig {

    /**
     * Creates a CorsFilter bean to handle CORS requests.
     *
     * @return A CorsFilter bean configured to allow requests from `http://localhost:4200`.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200"); // Adjust the origin as needed
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config); // Match all paths
        return new CorsFilter(source);
    }
}