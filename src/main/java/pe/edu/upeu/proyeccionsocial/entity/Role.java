package pe.edu.upeu.proyeccionsocial.entity;

import lombok.Data; // De la dependencia Lombok
import jakarta.persistence.*; // De Spring Data JPA

@Entity
@Table(name = "Roles")
@Data // Genera getters, setters, toString, etc.
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // PK auto-incrementable

    // Ejemplo: 'Administrador', 'Coordinador', 'Docente', 'Beneficiario'
    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    // Relación OneToMany: Un Rol puede estar asignado a muchos Usuarios (a través de UserRole)
    // Se mapea en la clase UserRole
    // No la ponemos aquí para mantenerla limpia, pero es una relación ManyToMany implícita
}