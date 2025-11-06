package pe.edu.upeu.proyeccionsocial.service;

import pe.edu.upeu.proyeccionsocial.entity.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll(); // Listar todos los roles

    Optional<Role> findById(Integer id); // Buscar por ID

    Optional<Role> findByNombre(String nombre); // Buscar por nombre (ej. "Coordinador")

    Role save(Role role); // Guardar o actualizar un rol
}