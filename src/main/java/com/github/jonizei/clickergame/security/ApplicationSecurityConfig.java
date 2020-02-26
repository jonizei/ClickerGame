package com.github.jonizei.clickergame.security;

import com.github.jonizei.clickergame.applicationuser.ApplicationUserRepository;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserService;
import com.github.jonizei.clickergame.jwt.JwtConfig;
import com.github.jonizei.clickergame.jwt.JwtLoginAuthenticationFilter;
import com.github.jonizei.clickergame.jwt.JwtTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

/**
 * This class handles security of the application
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Password encoder for passwords
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Service that is used for authentication
     */
    private final ApplicationUserService applicationUserService;

    /**
     * Class that holds information for a jwt token
     */
    private final JwtConfig jwtConfig;

    /**
     * SecretKey for a jwt token
     */
    private final SecretKey secretKey;

    /**
     * Constructor of ApplicationSecurityConfig
     *
     * @param passwordEncoder Password encoder for passwords
     * @param applicationUserService Service that is used for authentication
     * @param jwtConfig Class that holds information for jwt token
     * @param secretKey SecretKey for a jwt token
     */
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     ApplicationUserService applicationUserService,
                                     JwtConfig jwtConfig,
                                     SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    /**
     * This method configures security of the application.
     * - It disables CSRF
     * - Sets session creation policy to STATELESS
     * - Adds JwtLoginAuthenticationFilter to the FilterChain
     * - Add JwtTokenVerifier to the FilterChain after JwtLoginAuthenticationFilter
     * - Sets paths that doesn't need authentication to access
     * - Any other requests needs to be authenticated
     *
     * @param http Class that handles application security
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .requiresChannel()
                    .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                    .requiresSecure()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .addFilter(new JwtLoginAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtLoginAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/static/**", "/api/user/register").permitAll()
                .anyRequest()
                .authenticated();

    }

    /**
     * This method configures which authentication provider the
     * authentication manager will use.
     *
     * @param auth Class that builds the authentication manager
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * This method initializes the authentication provider that
     * the authentication manager will use
     *
     * @return DaoAuthenticationProvider object
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

}
