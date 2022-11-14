package com.gd.clinic.security.service;


import com.gd.clinic.security.entity.Role;
import com.gd.clinic.security.enums.RoleName;
import com.gd.clinic.security.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepo.findByRoleName(roleName);
    }

    public void save(Role role){
        roleRepo.save(role);
    }
}
