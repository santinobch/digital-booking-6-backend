package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.repository.UsuarioRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements BaseService<Usuario>{
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario element) {
        if(usuarioRepository.findByUsername(element.getUsername()) != null){
            throw new BusinessException("BL-100", "El nombre de usuario ya existe", HttpStatus.CONFLICT);
        } else if (usuarioRepository.findByEmail(element.getEmail()) != null){
            throw new BusinessException("BL-101", "El mail ya se encuentra registrado", HttpStatus.CONFLICT);
        }

        return usuarioRepository.save(element);
    }

    @Override
    public List<Usuario> getAll() {
        List usuariosEncontrados = usuarioRepository.findAll();
        if(usuariosEncontrados.isEmpty()){
            throw new ResourceNotFoundException("NF-100", "No hay usuarios registrados en la base de datos");
        }
        return usuariosEncontrados;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-101", "No existe el usuario con ID " + id);
        }
        usuarioRepository.deleteById(id);
        return true;
    }

    @Override
    public Usuario modify(Integer id, Usuario element) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-101", "No existe el usuario con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public Usuario getById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-101", "No existe el usuario con ID " + id));
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
