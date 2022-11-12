package com.gd.clinic.config.security.jwt;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String password;
}
