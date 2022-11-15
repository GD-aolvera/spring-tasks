package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.model.CredentialsDto;
import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements AdminApi {

    @Override
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> login(CredentialsDto credentialsDto) {
        return null;
    }
}
