package pe.edu.upeu.proyeccionsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data // Provee Getters, Setters, toString, hashCode, equals (Lombok)
@Entity // Define esta clase como una tabla de la base de datos
@Table(name = "projects") // Nombre de la tabla
public class Project {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name; // Nombre del proyecto

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Descripción detallada

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // Fecha de inicio del proyecto

    @Column(name = "end_date")
    private LocalDate endDate; // Fecha de fin (opcional)

    @Column(name = "status", length = 50)
    private String status; // Estado (Ej: "En Ejecución", "Finalizado", "Pendiente")

    // Relación con el usuario responsable (muchos proyectos a un usuario)
    // Se recomienda usar el ID de usuario del SSO para esto, pero por ahora usamos la entidad User.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_user_id", nullable = false)
    private User responsibleUser;
}