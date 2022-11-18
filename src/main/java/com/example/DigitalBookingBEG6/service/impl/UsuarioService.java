package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
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
        return null;
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
