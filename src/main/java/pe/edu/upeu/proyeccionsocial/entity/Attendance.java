package pe.edu.upeu.proyeccionsocial.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación Many-to-One: Muchas asistencias a un solo usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "timestamp_registro", nullable = false)
    private LocalDateTime timestampRegistro; // Momento exacto de la asistencia (manual)

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_registro", nullable = false)
    private TipoRegistro tipoRegistro; // ENTRADA o SALIDA

    @Column(name = "registro_manual", nullable = false)
    private Boolean registroManual = true; // Por defecto TRUE para este formulario

    // Enum para el tipo de registro
    public enum TipoRegistro {
        ENTRADA, SALIDA
    }

    // Campo opcional para auditoría: quién hizo el registro manual
    // Por ahora, lo omitimos si no estamos en seguridad, pero es una buena práctica.
}