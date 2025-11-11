package pe.edu.upeu.proyeccionsocial.service.impl;

import pe.edu.upeu.proyeccionsocial.entity.Role;
import pe.edu.upeu.proyeccionsocial.repository.RoleRepository;
import pe.edu.upeu.proyeccionsocial.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByNombre(String nombre) {
        return roleRepository.findByNombre(nombre);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}