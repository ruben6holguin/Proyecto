package pe.edu.upeu.proyeccionsocial.service.impl;

import pe.edu.upeu.proyeccionsocial.entity.Session;
import pe.edu.upeu.proyeccionsocial.repository.SessionRepository;
import pe.edu.upeu.proyeccionsocial.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session createSession(Integer userId, String token, Integer expirationInSeconds) {
        Session session = new Session();
        // Nota: En un sistema real, necesitarías buscar la entidad User para el FK,
        // pero por simplicidad de la tarea, solo usamos el ID en el repository.

        session.setToken(token);
        session.setUsuarioId(userId);
        session.setFechaCreacion(LocalDateTime.now());
        session.setFechaExpiracion(LocalDateTime.now().plusSeconds(expirationInSeconds));
        session.setActiva(true);

        return sessionRepository.save(session);
    }

    @Override
    public Optional<Session> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public void invalidateSession(String token) {

    }

    // ... (Implementación de findByToken, invalidateSession) ...

    @Override
    public boolean isTokenValidAndActive(String token) {
        return sessionRepository.findByToken(token)
                .filter(Session::getActiva) // Debe estar activa
                .filter(s -> s.getFechaExpiracion().isAfter(LocalDateTime.now())) // Debe no haber expirado
                .isPresent();
    }
}