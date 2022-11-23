package com.gd.clinic.security.service;

import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    final UserRepo userRepo;

    public Optional<User> getByUserName(String username){
        return userRepo.findOneByUserName(username);
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUserName(username);
    }

    public void save(User user){
        userRepo.save(user);
    }
}
