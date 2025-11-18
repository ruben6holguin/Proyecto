package pe.edu.upeu.proyeccionsocial.service;

import pe.edu.upeu.proyeccionsocial.entity.Attendance;
import pe.edu.upeu.proyeccionsocial.dto.ManualAttendanceDTO;

import java.util.List;

public interface AttendanceService {

    // Método clave para el registro manual
    Attendance registerManualAttendance(ManualAttendanceDTO dto);

    List<Attendance> findAll();
}