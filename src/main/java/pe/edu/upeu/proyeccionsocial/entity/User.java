package pe.edu.upeu.proyeccionsocial.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set; // Usado para colecciones de relaciones

@Entity
@Table(name = "Usuarios")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // PK auto-incrementable

    @Column(name = "codigo_lan", unique = true, length = 15)
    private String codigoLan; // Campo para el código UPeU (puede ser nulo si es Beneficiario)

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    // Columna ENUM: 'Lamb' o 'Beneficiario'
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ingreso", nullable = false)
    private TipoIngreso tipoIngreso;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true; // Por defecto: TRUE

    // Relación de Roles (Clave Foránea Mapeada)
    // Mapeo ManyToMany a través de la tabla UserRole (tabla pivote)
    @ManyToMany(fetch = FetchType.EAGER) // Carga los roles al cargar el usuario
    @JoinTable(
            name = "usuario_rol", // Nombre de la tabla pivote (UserRole)
            joinColumns = @JoinColumn(name = "usuario_id"), // FK de esta tabla (User)
            inverseJoinColumns = @JoinColumn(name = "rol_id") // FK de la otra tabla (Role)
    )
    private Set<Role> roles;

    // Clase interna para el ENUM TipoIngreso (opcional, pero limpio)
    public enum TipoIngreso {
        Lamb, Beneficiario
    }
}