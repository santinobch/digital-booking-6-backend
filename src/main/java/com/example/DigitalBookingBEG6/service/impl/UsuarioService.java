package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.repository.UsuarioRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements BaseService<Usuario> {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario element) {
        return usuarioRepository.save(element);
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        boolean deleted = false;
        try{
            Optional<Usuario> opt = usuarioRepository.findById(id);
            if(opt.isPresent()){
                usuarioRepository.deleteById(id);
                deleted = true;
            }
        }catch (Exception e){
            throw e;
        }
        return deleted;
    }

    @Override
    public Usuario modify(Integer id, Usuario element) {
        Usuario usuario = new Usuario();
        try{
            Optional<Usuario> opt = usuarioRepository.findById(id);
            if(opt.isPresent()){
                element.setId(id);
                usuario =  this.save(element);
            }
        }catch (Exception e){
            throw e;
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
