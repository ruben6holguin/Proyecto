package pe.edu.upeu.proyeccionsocial.controller;

import pe.edu.upeu.proyeccionsocial.entity.Role;
import pe.edu.upeu.proyeccionsocial.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles") // Endpoint base: /api/v1/roles
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Solo el ADMINISTRADOR puede ver la lista de Roles
    // (Implementación de permisos basada en Spring Security)
    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    // Solo el ADMINISTRADOR puede crear nuevos Roles
    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.save(role);
        // Devolver código 201 Created
        return ResponseEntity.status(201).body(newRole);
    }

    // El ADMINISTRADOR y el COORDINADOR pueden (o no) editar roles
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'COORDINADOR')")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role roleDetails) {
        return roleService.findById(id)
                .map(role -> {
                    role.setNombre(roleDetails.getNombre());
                    return ResponseEntity.ok(roleService.save(role));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}