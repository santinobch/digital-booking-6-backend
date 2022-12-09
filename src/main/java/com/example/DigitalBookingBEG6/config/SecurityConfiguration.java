package com.example.DigitalBookingBEG6.config;

import com.example.DigitalBookingBEG6.jwt.JwtRequestFilter;
import com.example.DigitalBookingBEG6.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/").permitAll()
                .antMatchers(HttpMethod.POST, "/users/").permitAll()
                .antMatchers("/roles/**").permitAll()
                .antMatchers("/register/").permitAll()
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/cities/**").permitAll()
                .antMatchers("/features/").permitAll()
                .antMatchers("/images/**", "/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/products/**").permitAll()
                .antMatchers( "/products/city/**").permitAll()
                .antMatchers( "/products/category/**").permitAll()
                .antMatchers(HttpMethod.POST,"/products/").hasAuthority("Administrador")
                .antMatchers(HttpMethod.POST, "/bookings/").hasAnyAuthority("Usuario", "Administrador")
                .antMatchers( "/products/category/**").permitAll()
                .antMatchers( "/bookings/product/**").permitAll()
                .antMatchers( "/swagger","/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/configuration/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
