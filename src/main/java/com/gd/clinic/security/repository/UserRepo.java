package com.gd.clinic.security.repository;

import com.gd.clinic.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findOneByUserName(String userName);
    boolean existsByUserName(String userName);

}
