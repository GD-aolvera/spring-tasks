package com.gd.clinic.security.util;

import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.service.UserMain;
import com.gd.clinic.security.enums.RoleName;
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
    UserDetailServiceImpl userService;

    @Override
    public void run(String... args) {
        User initAdmin = new User("Super", "User", "admin", new BCryptPasswordEncoder().encode("admin"), RoleName.ROLE_ADMIN.name());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(initAdmin.getRole()));
        UserMain.build(initAdmin);
        UserMain.build(initAdmin).setAuthorities(authorityList);
        userService.save(initAdmin);
    }
}
