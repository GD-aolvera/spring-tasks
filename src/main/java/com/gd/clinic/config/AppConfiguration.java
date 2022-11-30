package com.gd.clinic.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configurable
@PropertySource("classpath:application.yml")
public class AppConfiguration {

    @Value("${myapp.access.token.secret}")
    private  String accessTokenSecret;


    @Bean
    public AppConfiguration appConfiguration1 (){
        return new AppConfiguration();
    }

    public  String getAccessTokenSecret() {
        return this.accessTokenSecret;
    }

}
