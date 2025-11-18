package pe.edu.upeu.proyeccionsocial.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ManualAttendanceDTO {
    // Identificación del usuario: se puede usar codigoLan O email
    private String identifier; // Código LAN o Email del usuario

    // Fecha y hora combinadas que se van a registrar
    private LocalDateTime timestampRegistro;

    // Tipo de registro
    private String tipoRegistro; // "ENTRADA" o "SALIDA"
}