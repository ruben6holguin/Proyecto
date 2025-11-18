package pe.edu.upeu.proyeccionsocial.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BeneficiaryRegisterDTO {
    // Datos principales del User
    private String nombres;
    private String apellidos;
    private String email;

    // Datos del BeneficiaryProfile
    private String identification; // DNI/Cédula
    private LocalDate birthDate; // Importante usar java.time.LocalDate
    private String phone;
    private String gender;
    private String nationality;
}