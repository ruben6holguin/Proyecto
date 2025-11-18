package pe.edu.upeu.proyeccionsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.proyeccionsocial.entity.User;
import pe.edu.upeu.proyeccionsocial.service.UserService;
import pe.edu.upeu.proyeccionsocial.dto.BeneficiaryRegisterDTO;
import java.util.Map;

/**
 * Controlador REST para manejar las operaciones relacionadas con Beneficiarios.
 * La URL base es /api/v1/beneficiaries
 */
@RestController
@RequestMapping("/api/v1/beneficiaries")
@CrossOrigin(origins = "*")
public class BeneficiaryController {

    private final UserService userService;

    @Autowired
    public BeneficiaryController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para el registro de un nuevo Beneficiario a través del formulario.
     * Recibe un BeneficiaryRegisterDTO con todos los campos (incluyendo perfil).
     * Ruta: POST /api/v1/beneficiaries/register
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerBeneficiary(@RequestBody BeneficiaryRegisterDTO beneficiaryDto) {
        try {
            // El UserService se encarga de:
            // 1. Crear la entidad User.
            // 2. Crear la entidad BeneficiaryProfile relacionada.
            User newBeneficiary = userService.saveBeneficiario(beneficiaryDto);

            // Respuesta de éxito (201 Created)
            return new ResponseEntity(Map.of("id", newBeneficiary.getId(), "email", newBeneficiary.getEmail()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Maneja el caso de email o DNI/Cédula duplicado
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        } catch (RuntimeException e) {
            // Maneja errores internos (ej. rol no encontrado, PasswordEncoder no configurado)
            return new ResponseEntity("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}