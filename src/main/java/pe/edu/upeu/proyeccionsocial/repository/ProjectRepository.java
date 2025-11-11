package pe.edu.upeu.proyeccionsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.proyeccionsocial.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Método personalizado: Buscar proyectos por nombre (útil para búsquedas)
    List<Project> findByNameContainingIgnoreCase(String name);

    // Método personalizado: Buscar proyectos por usuario responsable
    List<Project> findByResponsibleUser_Id(Integer userId);
}