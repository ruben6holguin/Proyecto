package pe.edu.upeu.proyeccionsocial.service.Impl;

import pe.edu.upeu.proyeccionsocial.entity.User;
import pe.edu.upeu.proyeccionsocial.entity.Role;
import pe.edu.upeu.proyeccionsocial.repository.UserRepository;
import pe.edu.upeu.proyeccionsocial.service.UserService;
import pe.edu.upeu.proyeccionsocial.service.RoleService; // Necesario para buscar roles
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService; // Dependencia del servicio de roles

    // ... (Implementación de findAll, findById, findByCodigoLan, saveBeneficiario) ...

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByCodigoLan(String codigoLan) {
        return Optional.empty();
    }

    @Override
    public User saveBeneficiario(User user) {
        return null;
    }

    // Lógica para la Tarea "Implementar la asignación de roles al usuario (Backend)."
    @Override
    public User assignRoles(Integer userId, List<String> roleNames) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Set<Role> newRoles = roleNames.stream()
                .map(name -> roleService.findByNombre(name)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + name)))
                .collect(Collectors.toSet());

        user.setRoles(newRoles);
        return userRepository.save(user); // Guardar el usuario con la nueva colección de roles
    }
}