package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.exception.AdminNotFoundException;
import com.gd.clinic.model.CredentialsDto;
import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.UserResponseDto;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.*;

@Log4j2
@RestController
public class AdminController implements AdminApi {
    //adminService
    @Override
    public ResponseEntity<UserResponseDto> createUser(NewUserDto newUserDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> login(CredentialsDto credentialsDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> getAdmin(CredentialsDto credentialsDto) throws AdminNotFoundException {
        //Log.debug("entered getadmin");
        AdminApi a= adminService.getbyID();
        return ResponseEntity.ok(a);
    }
}
