package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.jwt.JwtUtil;
import com.example.DigitalBookingBEG6.model.dto.AuthenticationRequestDTO;
import com.example.DigitalBookingBEG6.model.dto.AuthenticationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/auth/")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO AuthenticationRequestDTO) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    AuthenticationRequestDTO.getUsername(),
                    AuthenticationRequestDTO.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(AuthenticationRequestDTO.getUsername());
        //generamos el token JWT
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDTO((jwt)));
    }
}
