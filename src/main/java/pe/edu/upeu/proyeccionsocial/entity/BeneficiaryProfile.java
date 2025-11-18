package pe.edu.upeu.proyeccionsocial.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "beneficiary_profiles")
@Data
public class BeneficiaryProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación One-to-One con User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user; // FK al usuario principal

    @Column(name = "identification", unique = true, nullable = false, length = 20)
    private String identification; // DNI/Cédula

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate; // Fecha de Nacimiento

    @Column(name = "phone", length = 20)
    private String phone; // Teléfono

    @Column(name = "gender", length = 20)
    private String gender; // Género

    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality; // Nacionalidad
}