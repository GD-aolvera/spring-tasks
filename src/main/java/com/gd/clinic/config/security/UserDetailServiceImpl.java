package com.gd.clinic.config.security;

import com.gd.clinic.entities.User;
import com.gd.clinic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findOneByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + userName + " does not exist"));
        return new UserDetailsImpl(user);
    }

}
