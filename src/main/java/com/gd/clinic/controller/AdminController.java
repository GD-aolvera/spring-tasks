package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.model.*;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.enums.RoleName;
import com.gd.clinic.security.service.RefreshTokenService;
import com.gd.clinic.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;


@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    final UserService userService;
    final RefreshTokenService refreshTokenService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        return ResponseEntity.ok(userService.createUser(newUserDto));
    }

    @Override
    public ResponseEntity<Void> login(CredentialsDto credentialsDto) {
        return null;
    }

    @Override
    public ResponseEntity<JwtResponseDto> tokenRefresh(JwtRefreshRequestDto jwtRefreshRequestDto) {
        return ResponseEntity.ok(refreshTokenService.refreshToken(jwtRefreshRequestDto));
    }




}
