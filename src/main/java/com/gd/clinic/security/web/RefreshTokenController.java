package com.gd.clinic.security.web;

import com.gd.clinic.security.application.RefreshTokenService;
import com.gd.clinic.security.jwt.JwtRefreshRequestDto;
import com.gd.clinic.security.jwt.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public JwtResponseDto refreshJwt(@RequestBody JwtRefreshRequestDto refreshRequestDto) {
        return refreshTokenService.refreshToken(refreshRequestDto);
    }

}
