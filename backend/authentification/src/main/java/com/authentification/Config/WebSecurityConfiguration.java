package com.authentification.Config;


import com.authentification.Util.KeycloakJwtRolesConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Value("${keycloak.client-id}")
    private String kcClientId;

    @Value("${keycloak.issuer-url}")
    private String tokenIssuerUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint entryPoint,
                                                   CustomAccessDenied accessDenied) throws Exception {

        DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter = new DelegatingJwtGrantedAuthoritiesConverter(
                new JwtGrantedAuthoritiesConverter(),
                new KeycloakJwtRolesConverter(kcClientId));

        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v1/admin")
                                .hasRole("admin")
                                .requestMatchers("/api/v1/technicien")
                                .hasRole("technicien")
                                .requestMatchers("/api/v1/user")
                                .hasRole("user")
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(accessDenied)
                .and()
                .csrf().disable()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(
                        jwt -> new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt))
                );
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(tokenIssuerUrl);
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}