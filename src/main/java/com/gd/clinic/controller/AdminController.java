package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.config.security.entity.User;
import com.gd.clinic.model.CredentialsDto;
import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.UserResponseDto;
import com.gd.clinic.config.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements AdminApi {

    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        userRepo.save(configUser(newUserDto));
        return ResponseEntity.ok(configUserResponse(userRepo.findOneByUserName(newUserDto.getUserName()).get()));
    }

    @Override
    public ResponseEntity<Void> login(CredentialsDto credentialsDto) {
        return null;
    }


    private User configUser(NewUserDto newUserDto){
        return new User(newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getUserName(), new BCryptPasswordEncoder().encode(newUserDto.getPassword()));
    }

    private UserResponseDto configUserResponse(User user){
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId().toString());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUserName(user.getUserName());
        response.setPassword(user.getPassword());
        return response;
    }
}
