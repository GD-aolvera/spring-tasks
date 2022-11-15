package com.gd.clinic.security;

import com.gd.clinic.security.application.RefreshTokenService;
import com.gd.clinic.security.jwt.JWTAuthenticationFilter;
import com.gd.clinic.security.jwt.JWTAuthorizationFilter;
import com.gd.clinic.security.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {


    @Autowired
    private final UserDetailServiceImpl userDetailsService;

    @Autowired
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    RefreshTokenService refreshTokenService;



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(refreshTokenService);
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");

        return http
                .csrf().disable()
                .authenticationProvider(authenticationProvider())
                .authorizeRequests()
                .antMatchers(
                        "/swagger-ui/**",
                                    "/swagger/resources/**",
                                    "/configuration/**",
                                    "/v3/api-docs/**",
                                    "/auth/login/**",
                                    "/h2-console/**"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and().headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @SneakyThrows
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return  authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService setUserDetailsServiceFactoryBean(){return new UserDetailServiceImpl();}


}

