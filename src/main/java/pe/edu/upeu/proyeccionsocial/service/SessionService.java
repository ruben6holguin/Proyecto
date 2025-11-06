package pe.edu.upeu.proyeccionsocial.service;

import pe.edu.upeu.proyeccionsocial.entity.Session;
import java.util.Optional;

public interface SessionService {

    Session createSession(Integer userId, String token, Integer expirationInSeconds);

    Optional<Session> findByToken(String token);

    void invalidateSession(String token); // Cierre de sesión (logout)

    boolean isTokenValidAndActive(String token); // Validación de seguridad
}