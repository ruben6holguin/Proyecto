package pe.edu.upeu.proyeccionsocial.service;

import pe.edu.upeu.proyeccionsocial.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll(); // Listar todos los usuarios

    Optional<User> findById(Integer id); // Buscar por ID

    Optional<User> findByCodigoLan(String codigoLan); // Buscar por código UPeU

    User saveBeneficiario(User user); // Guardar un Beneficiario (registro manual)

    // Método clave para la tarea de Asignación de Roles (Día 3)
    User assignRoles(Integer userId, List<String> roleNames);
}