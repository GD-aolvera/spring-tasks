package com.gd.clinic.security.service;

import com.gd.clinic.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userService.getByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + userName + " does not exist"));
        return UserMain.build(user);
    }

    public void save(User user) {
        userService.save(user);
    }

}
