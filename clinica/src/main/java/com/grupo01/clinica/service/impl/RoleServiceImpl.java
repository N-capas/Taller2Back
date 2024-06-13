package com.grupo01.clinica.service.impl;

import com.grupo01.clinica.domain.entities.Role;
import com.grupo01.clinica.repositorie.RoleRepository;
import com.grupo01.clinica.service.contracts.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findByCode(id);
    }

}
