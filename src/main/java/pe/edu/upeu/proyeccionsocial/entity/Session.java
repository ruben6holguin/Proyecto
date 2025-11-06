package pe.edu.upeu.proyeccionsocial.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Sesiones")
@Data
public class Session {

    @Id
    // El 'token' se usa como Clave Primaria según tu diseño,
    // pero a menudo se prefiere un ID simple. Usaremos el token como PK aquí.
    @Column(name = "token", length = 255)
    private String token;

    // Llave Foránea a la tabla Usuarios
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false) // Columna FK en esta tabla
    private User usuario;

    // Necesitamos el ID del usuario para el FK
    @Column(name = "usuario_id", insertable = false, updatable = false)
    private Integer usuarioId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fechaExpiracion;

    // Campo para saber si la sesión fue cerrada manualmente o expiró.
    @Column(name = "activa")
    private Boolean activa = true;
}