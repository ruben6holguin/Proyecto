package pe.edu.upeu.proyeccionsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.proyeccionsocial.entity.BeneficiaryProfile;

@Repository
public interface BeneficiaryProfileRepository extends JpaRepository<BeneficiaryProfile, Integer> {

}