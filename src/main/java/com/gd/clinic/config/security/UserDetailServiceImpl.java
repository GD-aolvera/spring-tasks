package com.gd.clinic.config.security;

import com.gd.clinic.model.UserResponseDto;
import com.gd.clinic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponseDto user = userRepo.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " does not exist"));
        return new UserDetailsImpl(user);
    }

}
