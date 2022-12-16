package com.gd.clinic.security.service;

import com.gd.clinic.model.NewUserDto;
import com.gd.clinic.model.UserResponseDto;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.mapper.MapNewUserDtoToUser;
import com.gd.clinic.security.repository.UserRepo;
import com.gd.clinic.security.util.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final MapNewUserDtoToUser mapNewUserDtoToUser;

    public Optional<User> getByUserName(String username) {
        return userRepo.findOneByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public UserResponseDto save(User user) {
        user.setCreatedBy(AuthInfo.getCurrentUser());
        user.setCreatedAt(OffsetDateTime.now());
        userRepo.save(user);
        return configUserResponse(user);
    }

    public UserResponseDto createUser(NewUserDto newUserDto) throws ResponseStatusException {
        if (existsByUsername(newUserDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        User user = configUser(newUserDto);
        user.setPassword(new BCryptPasswordEncoder().encode(newUserDto.getPassword()));
        user.setRole(UserResponseDto.RoleEnum.fromValue(user.getRole().toLowerCase()).name());
        return save(user);
    }

    private User configUser(NewUserDto newUserDto) {
        return mapNewUserDtoToUser.newUserToUser(newUserDto);
    }

    private UserResponseDto configUserResponse(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId().toString());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setRole(UserResponseDto.RoleEnum.valueOf(user.getRole()));
        response.setCreatedAt(user.getCreatedAt());
        response.setCreatedBy(user.getCreatedBy());
        return response;
    }

}
