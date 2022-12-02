package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.model.dto.UsuarioCreacionDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.repository.UsuarioRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements BaseService<UsuarioDTO>{
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private RolService rolService;

    @Autowired
    private GenericModelMapper genericModelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO element) {
        if(usuarioRepository.findByUsername(element.getUsername()).isPresent()){
            throw new BusinessException("BL-100", "El nombre de usuario ya existe", HttpStatus.CONFLICT);
        } else if (usuarioRepository.findByUsuarioEmail(element.getEmail()).isPresent()){
            throw new BusinessException("BL-101", "El mail ya se encuentra registrado", HttpStatus.CONFLICT);
        }
        Usuario usuario = usuarioRepository.save(genericModelMapper.mapToUsuario(element));
        return genericModelMapper.mapToUsuarioDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> getAll() {
        List<Usuario> listadoUsuarios = usuarioRepository.findAll();
        if(listadoUsuarios.isEmpty()){
            throw new ResourceNotFoundException("NF-100", "No hay usuarios registrados en la base de datos");
        }
        return listadoUsuarios.stream().map(e -> genericModelMapper.mapToUsuarioDTO(e)).collect(Collectors.toList());
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
    public UsuarioDTO modify(Integer id, UsuarioDTO element) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-101", "No existe el usuario con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public UsuarioDTO getById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-101", "No existe el usuario con ID " + id));
        return genericModelMapper.mapToUsuarioDTO(usuario);
    }

    public UsuarioDTO register(UsuarioCreacionDTO element){
        if(usuarioRepository.findByUsername(element.getUsername()).isPresent()){
            throw new BusinessException("BL-100", "El nombre de usuario ya existe", HttpStatus.CONFLICT);
        } else if (usuarioRepository.findByUsuarioEmail(element.getEmail()).isPresent()){
            throw new BusinessException("BL-101", "El mail ya se encuentra registrado", HttpStatus.CONFLICT);
        }
        Usuario usuario = genericModelMapper.mapToUsuarioCreacion(element);
        usuario.setUsuarioRol(genericModelMapper.mapToRol(rolService.getByNombre("Usuario")));

        return genericModelMapper.mapToUsuarioDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("NF-101", "No existe el usuario con email " + email));

        return genericModelMapper.mapToUsuarioDTO(usuario);
    }

    public UsuarioDTO findByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("NF-101", "No existe el username " + username));

        return genericModelMapper.mapToUsuarioDTO(usuario);
    }

    public Usuario loadByUsername(String username){
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("NF-101", "No existe el username " + username));

    }
}
