package pe.edu.upeu.proyeccionsocial.repository;

import pe.edu.upeu.proyeccionsocial.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Método custom para buscar un rol por su nombre (ej. "Administrador").
    // Útil para la lógica de asignación de roles.
    Optional<Role> findByNombre(String nombre);
}