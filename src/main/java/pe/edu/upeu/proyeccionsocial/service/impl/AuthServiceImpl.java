package pe.edu.upeu.proyeccionsocial.service.impl;

import pe.edu.upeu.proyeccionsocial.entity.User;
import pe.edu.upeu.proyeccionsocial.entity.Role;
import pe.edu.upeu.proyeccionsocial.repository.UserRepository;
import pe.edu.upeu.proyeccionsocial.repository.SessionRepository;
import pe.edu.upeu.proyeccionsocial.service.AuthService;
import pe.edu.upeu.proyeccionsocial.security.JwtUtil; // 🔑 NUEVO IMPORT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private JwtUtil jwtUtil; // 🔑 NUEVA INYECCIÓN DEL UTILITY

    // --- LÓGICA DE NEGOCIO ---
    private final Set<String> ROLES_CON_ACCESO = Set.of("Administrador", "Coordinador", "Docente", "Estudiante");

    @Override
    public String handleSsoLogin(Map<String, Object> lanData) {

        String codigoLan = (String) lanData.get("codigo_lan");

        // 1. Busca/Crea al Usuario
        User user = findOrCreateUser(codigoLan, lanData);

        // 2. Validación de Autorización
        if (!hasAccessRole(user.getRoles())) {
            throw new SecurityException("Acceso Denegado. Rol insuficiente.");
        }

        // 3. Generación de Sesión Interna (JWT) 🔑 LÓGICA REAL
        String jwtToken = jwtUtil.generarToken(user.getEmail()); // Genera token usando el email

        // 4. Guardar Sesión (Opcional, pero recomendado para control)
        // Lógica de guardado iría aquí usando sessionRepository.save()

        return jwtToken;
    }

    @Override
    public User findOrCreateUser(String codigoLan, Map<String, Object> lanData) {
        Optional<User> existingUser = userRepository.findByCodigoLan(codigoLan);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            throw new RuntimeException("Usuario nuevo, se requiere lógica completa de creación.");
        }
    }

    @Override
    public boolean hasAccessRole(Set<Role> roles) {
        for (Role role : roles) {
            if (ROLES_CON_ACCESO.contains(role.getNombre())) {
                return true;
            }
        }
        return false;
    }

    // *** EL MÉTODO generatePlaceholderToken FUE REMOVIDO ***
}