package pe.edu.upeu.proyeccionsocial.service;

import pe.edu.upeu.proyeccionsocial.entity.User;
import pe.edu.upeu.proyeccionsocial.entity.Role;
import java.util.Map;
import java.util.Set;

public interface AuthService {

    // Método principal del SSO: Lógica de la tarea "Creación de Sesión Interna..."
    String handleSsoLogin(Map<String, Object> lanData);

    // Lógica para sincronización: busca o crea al usuario
    User findOrCreateUser(String codigoLan, Map<String, Object> lanData);

    // Lógica de validación de roles de acceso
    boolean hasAccessRole(Set<Role> roles);
}