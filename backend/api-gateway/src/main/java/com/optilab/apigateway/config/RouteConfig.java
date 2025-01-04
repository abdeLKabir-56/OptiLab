package com.optilab.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/v1/laboratoires/**")
                        .uri("lb://laboratoire"))
                .route(r -> r.path("/api/v1/contacts-laboratoire/**")
                        .uri("lb://laboratoire"))
                .route(r -> r.path("/api/v1/adresses/**")
                        .uri("lb://laboratoire"))
                .route(r-> r.path("/api/v1/auth/**")
                        .uri("lb://utilisateur"))
                .route(r-> r.path("/api/v1/roles/**")
                        .uri("lb://utilisateur"))
                .route(r-> r.path("/api/v1/utilisateurs/**")
                        .uri("lb://utilisateur"))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
