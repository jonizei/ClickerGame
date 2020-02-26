package com.github.jonizei.clickergame.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class configures the password encoder that will
 * be used in the application
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@Configuration
public class PasswordConfig {

    /**
     * Returns password encoder
     *
     * @return BCryptPasswordEncoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(10); }

}
