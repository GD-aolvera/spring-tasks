package com.gd.clinic.security.jwt;

import lombok.Data;

@Data
public class AuthCredentials {

    private String username;
    private String password;
}
