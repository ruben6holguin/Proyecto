package pe.edu.upeu.proyeccionsocial.controller;

import pe.edu.upeu.proyeccionsocial.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth") // Endpoint base: /api/v1/auth
public class AuthController {

    @Autowired
    private AuthService authService;

    // ENDPOINT SIMULADO DE LOGIN/SSO:
    // En la versión final, esta lógica estará en el Módulo Callback del Día 2
    @PostMapping("/login-sso")
    // Usamos @RequestBody para recibir los datos simulados que vendrían del LAN
    public ResponseEntity<Map<String, String>> ssoLogin(@RequestBody Map<String, Object> lanData) {

        try {
            // Llama al Servicio de Autenticación, donde ocurre la lógica de negocio simulada
            String jwtToken = authService.handleSsoLogin(lanData);

            // Si tiene éxito, devolvemos el token y el estado 200 OK.
            Map<String, String> response = Map.of(
                    "token", jwtToken,
                    "type", "Bearer",
                    "message", "Login SSO exitoso (Simulado)"
            );
            return ResponseEntity.ok(response);

        } catch (SecurityException e) {
            // Si falla la validación de roles
            return ResponseEntity.status(403) // Código 403: Prohibido
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            // Otros errores (ej. usuario nuevo sin lógica de creación)
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Aquí iría el @PostMapping("/logout") para cerrar la sesión
}