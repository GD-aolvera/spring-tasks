package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.model.*;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.enums.RoleName;
import com.gd.clinic.security.service.RefreshTokenService;
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


@RestController
public class AdminController implements AdminApi {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    RefreshTokenService refreshTokenService;


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        String role = "";
        User user = configUser(newUserDto);
        if(userService.existsByUsername(newUserDto.getUserName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        switch (newUserDto.getRole()) {
            case ADMIN -> role =  roleService.getByRoleName(RoleName.ROLE_ADMIN).get().getRoleName().name();
            case NURSE -> role =  roleService.getByRoleName(RoleName.ROLE_NURSE).get().getRoleName().name();
            case DOCTOR -> role = roleService.getByRoleName(RoleName.ROLE_DOCTOR).get().getRoleName().name();
        }
        user.setRole(role);
        userService.save(user);
        return ResponseEntity.ok(configUserResponse(userService.getByUserName(newUserDto.getUserName()).get()));
    }

    @Override
    public ResponseEntity<Void> login(CredentialsDto credentialsDto) {
        return null;
    }

    @Override
    public ResponseEntity<JwtResponseDto> tokenRefresh(JwtRefreshRequestDto jwtRefreshRequestDto) {
        return ResponseEntity.ok(refreshTokenService.refreshToken(jwtRefreshRequestDto));
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
        response.setRole(UserResponseDto.RoleEnum.fromValue(user.getRole().substring(5).toLowerCase()));
        response.setCreatedAt(user.getCreatedAt());
        response.setCreatedBy(user.getCreatedBy());
        return response;
    }
}
