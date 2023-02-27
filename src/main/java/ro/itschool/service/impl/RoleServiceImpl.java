package ro.itschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Role;
import ro.itschool.repository.RoleRepository;
import ro.itschool.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoles(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
