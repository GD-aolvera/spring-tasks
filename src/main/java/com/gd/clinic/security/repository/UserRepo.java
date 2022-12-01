package com.gd.clinic.security.repository;

import com.gd.clinic.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findOneByUsername(String username);

    boolean existsByUsername(String username);

}
