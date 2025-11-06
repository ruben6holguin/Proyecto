package pe.edu.upeu.proyeccionsocial.controller;

import pe.edu.upeu.proyeccionsocial.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/session") // Endpoint base: /api/v1/session
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // 🔑 Tarea: Cierre de Sesión (Logout)
    // Cualquier usuario logueado debe poder cerrar su propia sesión.
    @PostMapping("/logout")
    // En la implementación de seguridad final, obtendrás el token del header Authorization
    public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String authorizationHeader) {

        // Simulación: Extraer el token de la cabecera (Header)
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // Llama a la lógica de negocio para invalidar la sesión en la DB
            sessionService.invalidateSession(token);

            return ResponseEntity.ok(Map.of("message", "Sesión cerrada exitosamente."));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Token no proporcionado."));
    }

    // Aquí podría ir un endpoint /validate para verificar si el token sigue activo.
}