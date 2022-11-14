package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.security.entity.Role;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.model.CredentialsDto;
import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.UserResponseDto;
import com.gd.clinic.security.enums.RoleName;
import com.gd.clinic.security.service.RoleService;
import com.gd.clinic.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AdminController implements AdminApi {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        Set<Role> roles = new HashSet<>();
        User user = configUser(newUserDto);
        if(userService.existsByUsername(newUserDto.getUserName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (String role: newUserDto.getRole()) {
            switch (role){
                case "admin" -> roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
                case "nurse" -> roles.add(roleService.getByRoleName(RoleName.ROLE_NURSE).get());
                case "doctor" -> roles.add(roleService.getByRoleName(RoleName.ROLE_DOCTOR).get());
            }
        }
        user.setRole(roles);
        userService.save(user);
        return ResponseEntity.ok(configUserResponse(userService.getByUserName(newUserDto.getUserName()).get()));
    }

    @Override
    public ResponseEntity<Void> login(CredentialsDto credentialsDto) {
        return null;
    }


    private User configUser(NewUserDto newUserDto){
        User user = new User(newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getUserName(), new BCryptPasswordEncoder().encode(newUserDto.getPassword()));
        user.setCreatedAt(OffsetDateTime.now());
        user.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return user;
    }

    private UserResponseDto configUserResponse(User user){
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId().toString());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUserName(user.getUserName());
        response.setPassword(user.getPassword());
        response.setRole(user.getRole().stream().map(role -> role.getRoleName().name()).collect(Collectors.toList()));
        response.setCreatedAt(user.getCreatedAt());
        response.setCreatedBy(user.getCreatedBy());
        return response;
    }
}
