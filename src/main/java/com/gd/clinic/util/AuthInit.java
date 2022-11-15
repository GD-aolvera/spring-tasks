package com.gd.clinic.util;

import com.gd.clinic.security.entity.Role;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.entity.UserMain;
import com.gd.clinic.security.enums.RoleName;
import com.gd.clinic.security.service.RoleService;
import com.gd.clinic.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthInit implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserDetailServiceImpl userService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleDoctor = new Role(RoleName.ROLE_DOCTOR);
        Role roleNurse = new Role(RoleName.ROLE_NURSE);
        User initAdmin = new User("Super", "User", "admin", new BCryptPasswordEncoder().encode("admin"));
        Set<Role> initAdminRoles = new HashSet<>();
        roleService.save(roleAdmin);
        roleService.save(roleDoctor);
        roleService.save(roleNurse);
        initAdminRoles.add(roleAdmin);
        initAdmin.setRole(initAdminRoles);
        List<GrantedAuthority> authorityList = initAdmin.getRole().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        UserMain.build(initAdmin);
        UserMain.build(initAdmin).setAuthorities(authorityList);
        userService.save(initAdmin);
    }
}
