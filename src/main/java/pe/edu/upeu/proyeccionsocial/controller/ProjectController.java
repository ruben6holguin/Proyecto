package pe.edu.upeu.proyeccionsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.proyeccionsocial.entity.Project;
import pe.edu.upeu.proyeccionsocial.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // POST: /api/v1/projects
    // Permiso: Solo Administradores y Coordinadores pueden crear proyectos
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') or hasAuthority('ROLE_COORDINATOR')")
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    // GET: /api/v1/projects
    // Permiso: Todos los usuarios autenticados pueden ver la lista de proyectos
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET: /api/v1/projects/{id}
    // Permiso: Todos los usuarios autenticados
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));
        return ResponseEntity.ok(project);
    }

    // PUT: /api/v1/projects/{id}
    // Permiso: Solo Administradores y Coordinadores pueden actualizar
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') or hasAuthority('ROLE_COORDINATOR')")
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Integer id, @RequestBody Project projectDetails) {
        return projectService.updateProject(id, projectDetails);
    }

    // DELETE: /api/v1/projects/{id}
    // Permiso: Solo Administradores pueden eliminar
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    // GET: /api/v1/projects/responsible/{userId}
    // Permiso: Todos los usuarios autenticados
    @GetMapping("/responsible/{userId}")
    public List<Project> getProjectsByResponsibleUser(@PathVariable Integer userId) {
        return projectService.getProjectsByResponsibleUser(userId);
    }
}