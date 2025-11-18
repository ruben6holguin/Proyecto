package pe.edu.upeu.proyeccionsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.proyeccionsocial.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}