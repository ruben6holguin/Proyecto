package pe.edu.upeu.proyeccionsocial.service.impl;

import pe.edu.upeu.proyeccionsocial.entity.Attendance;
import pe.edu.upeu.proyeccionsocial.entity.Attendance.TipoRegistro;
import pe.edu.upeu.proyeccionsocial.entity.User;
import pe.edu.upeu.proyeccionsocial.repository.AttendanceRepository;
import pe.edu.upeu.proyeccionsocial.repository.UserRepository;
import pe.edu.upeu.proyeccionsocial.service.AttendanceService;
import pe.edu.upeu.proyeccionsocial.dto.ManualAttendanceDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository; // Necesario para buscar al usuario

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance registerManualAttendance(ManualAttendanceDTO dto) {

        // 1. Buscar al usuario por Codigo LAN o Email
        Optional<User> userOptional = userRepository.findByCodigoLan(dto.getIdentifier());
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByEmail(dto.getIdentifier());
        }

        User user = userOptional.orElseThrow(() ->
                new IllegalArgumentException("Usuario no encontrado con el identificador: " + dto.getIdentifier())
        );

        // 2. Crear la entidad Attendance
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setTimestampRegistro(dto.getTimestampRegistro());

        // 3. Determinar el TipoRegistro
        try {
            TipoRegistro tipo = TipoRegistro.valueOf(dto.getTipoRegistro().toUpperCase());
            attendance.setTipoRegistro(tipo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de registro inválido. Debe ser ENTRADA o SALIDA.");
        }

        // 4. Registrar y devolver
        // Nota: registroManual es true por defecto en la Entidad.
        return attendanceRepository.save(attendance);
    }
}