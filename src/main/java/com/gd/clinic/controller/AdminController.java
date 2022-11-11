package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.config.security.UserDetailServiceImpl;
import com.gd.clinic.entities.User;
import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController implements AdminApi {

    private UserDetailServiceImpl userDetails;

    @Override
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        return null;
    }

    @Override
    public ResponseEntity<String> login(String user, String password) {
        return null;
    }

    private User configUser(NewUserDto newUserDto){
        return new User(null, newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getUserName(), newUserDto.getPassword());
    }
}
