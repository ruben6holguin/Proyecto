package pe.edu.upeu.proyeccionsocial.controller;

import pe.edu.upeu.proyeccionsocial.entity.User;
import pe.edu.upeu.proyeccionsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // ⚠️ Necesario para proteger el endpoint
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users") // Endpoint base: /api/v1/users
public class UserController {

    @Autowired
    private UserService userService;

    // DTO que recibe la lista de roles a asignar (ej: ["Docente", "Coordinador"])
    public static class RoleAssignmentRequest {
        public List<String> roleNames;
    }

    // 🔑 Tarea: Implementar asignación de roles
    // Solo el rol 'ADMINISTRATOR' debe tener acceso a esta función.
    @PutMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')") // Protege el endpoint con Spring Security
    public ResponseEntity<User> assignRolesToUser(
            @PathVariable Integer id,
            @RequestBody RoleAssignmentRequest request)
    {
        try {
            // Llama a la lógica de negocio en el UserService
            User updatedUser = userService.assignRoles(id, request.roleNames);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            // Si el usuario o rol no se encuentra (lanzado desde el ServiceImpl)
            return ResponseEntity.badRequest().build();
        }
    }

    // Aquí iría el @GetMapping para listar todos los usuarios (requiere permisos)
}