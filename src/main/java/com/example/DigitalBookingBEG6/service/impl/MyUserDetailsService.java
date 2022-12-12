package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.loadByUsername(username);
        String rol = usuario.getUsuarioRol().getRolNombre();
        Set<GrantedAuthority> autorizaciones = new HashSet();
        autorizaciones.add(new SimpleGrantedAuthority(rol));

        return new User(username, usuario.getUsuarioPassword(), true, true, true, true, autorizaciones);
    }
}
