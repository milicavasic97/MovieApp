package com.webapi.movieapp.service;


import com.webapi.movieapp.models.Role;
import com.webapi.movieapp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(int id) throws NotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found role by id:" + id));
    }

    public Role update(Role role) throws NotFoundException {
        if (roleRepository.existsById(role.getRoleId())) {
            return roleRepository.save(role);
        }
        throw new NotFoundException("Not found role by id:" + role.getRoleId());
    }

    public void delete(Role role) throws NotFoundException {
        if (roleRepository.existsById(role.getRoleId())) {
            roleRepository.delete(role);
        }
        throw new NotFoundException("Not found role by id:" + role.getRoleId());
    }
}
