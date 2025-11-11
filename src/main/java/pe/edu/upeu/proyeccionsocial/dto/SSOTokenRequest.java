package pe.edu.upeu.proyeccionsocial.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para recibir el JWT enviado por el sistema de SSO (LAMB Simulado).
 */
@Data
public class SSOTokenRequest {

    @NotBlank(message = "El token JWT no puede estar vacío.")
    private String token;
}