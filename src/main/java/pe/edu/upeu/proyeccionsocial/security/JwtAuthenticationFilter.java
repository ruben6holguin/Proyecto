package pe.edu.upeu.proyeccionsocial.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.edu.upeu.proyeccionsocial.repository.UserRepository; // 🔑 Necesario para cargar usuario
import pe.edu.upeu.proyeccionsocial.entity.Role; // Necesario para mapear roles
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil; // 🔑 INYECCIÓN DEL UTILITY

    @Autowired
    private UserRepository userRepository; // 🔑 INYECCIÓN DEL REPOSITORIO

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.obtenerEmailDelToken(token);

        // Si el email es válido Y el usuario no está ya autenticado en el contexto de seguridad
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var user = userRepository.findByEmail(email).orElse(null);

            // Si el usuario existe y el token es válido
            if (user != null && jwtUtil.validarToken(token)) {

                // 1. Mapear roles a autoridades que Spring Security entiende
                var authorities = user.getRoles().stream()
                        .map(Role::getNombre)
                        // Usamos el prefijo "ROLE_" para que Spring Security lo reconozca
                        .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase()))
                        .collect(Collectors.toList());

                // 2. Crear el objeto de autenticación
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, authorities);

                // 3. Establecer el usuario en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}