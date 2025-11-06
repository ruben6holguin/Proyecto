package pe.edu.upeu.proyeccionsocial.repository;

import pe.edu.upeu.proyeccionsocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Método custom esencial para el SSO y la Sincronización (Tarea Día 3).
    // Permite buscar si el usuario ya existe en nuestra DB con el código del LAN.
    Optional<User> findByCodigoLan(String codigoLan);

    // Método para buscar un usuario por su email (útil para Beneficiarios y validación).
    Optional<User> findByEmail(String email);
}