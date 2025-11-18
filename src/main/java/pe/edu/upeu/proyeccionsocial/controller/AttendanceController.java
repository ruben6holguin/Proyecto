package pe.edu.upeu.proyeccionsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.proyeccionsocial.dto.ManualAttendanceDTO;
import pe.edu.upeu.proyeccionsocial.entity.Attendance;
import pe.edu.upeu.proyeccionsocial.service.AttendanceService;

@RestController
@RequestMapping("/api/v1/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Endpoint para registrar asistencia manual
    @PostMapping("/manual-register")
    // ⚠️ NOTA: Este endpoint DEBE ser protegido con @PreAuthorize('hasAnyAuthority("COORDINADOR", "ADMINISTRADOR")')
    // Lo omitimos temporalmente por la consigna de no usar seguridad aún.
    public ResponseEntity<?> registerManualAttendance(@RequestBody ManualAttendanceDTO dto) {
        try {
            Attendance newAttendance = attendanceService.registerManualAttendance(dto);
            // Devolver un DTO de respuesta simple
            return new ResponseEntity("Registro de asistencia manual creado para el usuario ID: " + newAttendance.getUser().getId(), HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            // Maneja errores de identificación o tipo de registro inválido
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        } catch (Exception e) {
            // Maneja otros errores internos
            return new ResponseEntity("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}