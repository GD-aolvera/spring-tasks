package com.gd.clinic.controller;

import com.gd.clinic.api.AdminApi;
import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.TreatmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController implements AdminApi {
    @Override
    public ResponseEntity<Void> createUser(NewUserDto newUserDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        return null;
    }

    @Override
    public ResponseEntity<Void> login(String user, String password) {
        return null;
    }
}
