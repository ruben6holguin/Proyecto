package pe.edu.upeu.proyeccionsocial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Habilita la configuración de seguridad de Spring
@EnableMethodSecurity(prePostEnabled = true) // Habilita @PreAuthorize (Roles)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // 1. Define el Codificador de Contraseñas (Buena práctica de seguridad)
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt es el estándar seguro para hashear contraseñas.
        return new BCryptPasswordEncoder();
    }

    // 2. Define el Administrador de Autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 3. Define la Cadena de Filtros de Seguridad (Reglas HTTP)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔒 Deshabilita CSRF: Es estándar para APIs REST que usan JWT (Stateless)
                .csrf(AbstractHttpConfigurer::disable)

                // 🔄 Configuración de Sesiones: Obligatorio para JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 🛣️ Configuración de Acceso a Endpoints
                .authorizeHttpRequests(authorize -> authorize
                        // Permite el acceso público al endpoint de login SSO (simulado) y logout
                        .requestMatchers("/api/v1/auth/login-sso", "/api/v1/session/logout")
                        .permitAll()

                        // Requiere autenticación (JWT) para CUALQUIER otra petición
                        .anyRequest().authenticated()
                );

        // 🔗 Agrega el Filtro JWT custom ANTES de que Spring procese la solicitud
        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}