package pe.edu.upeu.proyeccionsocial.service;

import pe.edu.upeu.proyeccionsocial.entity.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    // Crear un nuevo proyecto
    Project createProject(Project project);

    // Obtener todos los proyectos
    List<Project> getAllProjects();

    // Obtener un proyecto por ID
    Optional<Project> getProjectById(Integer id);

    // Actualizar un proyecto existente
    Project updateProject(Integer id, Project projectDetails);

    // Eliminar un proyecto
    void deleteProject(Integer id);

    // Obtener proyectos por usuario responsable (para el dashboard del responsable)
    List<Project> getProjectsByResponsibleUser(Integer userId);
}