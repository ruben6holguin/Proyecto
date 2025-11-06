package pe.edu.upeu.proyeccionsocial.repository;

import pe.edu.upeu.proyeccionsocial.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    // Usamos 'String' como PK porque la entidad Session usa el 'token' como Clave Primaria.

    // Método para buscar una sesión activa por su Token. Clave para la validación de cada request.
    Optional<Session> findByToken(String token);

    // Método útil: Buscar sesiones activas por usuario que aún no hayan expirado.
    Optional<Session> findByUsuarioIdAndActivaTrueAndFechaExpiracionAfter(
            Integer usuarioId,
            LocalDateTime now
    );
}