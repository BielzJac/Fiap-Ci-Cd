package br.com.fiap.gestaoresiduos.config.security;

import br.com.fiap.gestaoresiduos.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Autowired
    private AuthorizationService authorizationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Auth
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // Caminhoes
                        .requestMatchers(HttpMethod.GET, "/caminhoes/todos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/caminhoes/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/caminhoes/adicionar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/caminhoes/atualizar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/caminhoes/{id}").hasRole("ADMIN")

                        // Coletas
                        .requestMatchers(HttpMethod.GET, "/coletas/todos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/coletas/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/coletas/adicionar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/coletas/atualizar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/coletas/{id}").hasRole("ADMIN")

                        // Notificacoes
                        .requestMatchers(HttpMethod.GET, "/notificacoes/todos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/notificacoes/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/notificacoes/adicionar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/notificacoes/atualizar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/notificacoes/{id}").hasRole("ADMIN")

                        // Recipientes
                        .requestMatchers(HttpMethod.GET, "/recipientes/todos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/recipientes/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/recipientes/adicionar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/recipientes/atualizar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/recipientes/{id}").hasRole("ADMIN")

                        // Usuarios
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/atualizar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/{id}").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
