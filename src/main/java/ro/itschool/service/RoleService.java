package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Role;

@Service
public interface RoleService {

    public Role findRoles(String name);

    public Role saveRole(Role role);
}
