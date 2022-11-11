package com.gd.clinic.repository;

import com.gd.clinic.model.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserResponseDto, Integer> {

    Optional<UserResponseDto> findOneByUsername(String username);
}
