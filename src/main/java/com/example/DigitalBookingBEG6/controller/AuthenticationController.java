package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.jwt.JwtUtil;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.model.dto.AuthenticationRequestDTO;
import com.example.DigitalBookingBEG6.model.dto.AuthenticationResponseDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioCreacionDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.repository.UsuarioRepository;
import com.example.DigitalBookingBEG6.service.impl.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/auth/")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO AuthenticationRequestDTO) throws Exception{
        try {
            UsuarioDTO usuarioDTO = usuarioService.findByEmail(AuthenticationRequestDTO.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuarioDTO.getUsername(),
                    AuthenticationRequestDTO.getPassword()
            ));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioDTO.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponseDTO((jwt), userDetails.getUsername()));
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuarioDTO> registerUser(@Valid @RequestBody UsuarioCreacionDTO usuarioCreacionDTO){
        usuarioCreacionDTO.setPassword(passwordEncoder.encode(usuarioCreacionDTO.getPassword()));
        return ResponseEntity.status(201).body(usuarioService.register(usuarioCreacionDTO));
    }
}
