package pe.edu.upeu.proyeccionsocial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.proyeccionsocial.entity.Project;
import pe.edu.upeu.proyeccionsocial.repository.ProjectRepository;
import pe.edu.upeu.proyeccionsocial.repository.UserRepository;
import pe.edu.upeu.proyeccionsocial.service.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository; // Necesario para validar que el responsable exista

    @Override
    public Project createProject(Project project) {
        // Validación básica: Asegurar que el usuario responsable exista antes de guardar.
        if (project.getResponsibleUser() == null || project.getResponsibleUser().getId() == null) {
            throw new IllegalArgumentException("El proyecto debe tener un usuario responsable asignado.");
        }
        userRepository.findById(project.getResponsibleUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario responsable no encontrado."));

        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project updateProject(Integer id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));

        // 1. Actualizar campos
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setStatus(projectDetails.getStatus());

        // 2. Si se cambia el responsable, verificar que el nuevo exista
        if (projectDetails.getResponsibleUser() != null && !project.getResponsibleUser().getId().equals(projectDetails.getResponsibleUser().getId())) {
            userRepository.findById(projectDetails.getResponsibleUser().getId())
                    .orElseThrow(() -> new RuntimeException("Nuevo usuario responsable no encontrado."));
            project.setResponsibleUser(projectDetails.getResponsibleUser());
        }

        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));

        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> getProjectsByResponsibleUser(Integer userId) {
        return projectRepository.findByResponsibleUser_Id(userId);
    }
}