package com.gd.clinic.security.util;

import com.gd.clinic.security.entity.Role;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.service.UserMain;
import com.gd.clinic.security.enums.RoleName;
import com.gd.clinic.security.service.RoleService;
import com.gd.clinic.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        roleService.save(roleAdmin);
        roleService.save(roleDoctor);
        roleService.save(roleNurse);
        initAdmin.setRole(RoleName.ROLE_ADMIN.name());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(initAdmin.getRole()));
        UserMain.build(initAdmin);
        UserMain.build(initAdmin).setAuthorities(authorityList);
        userService.save(initAdmin);
    }
}
