package com.example.DigitalBookingBEG6.mappers;

import com.example.DigitalBookingBEG6.model.*;
import com.example.DigitalBookingBEG6.model.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;


@Component
public class GenericModelMapper {
    private final ModelMapper mapper = new ModelMapper();

    private static GenericModelMapper instance;

    private GenericModelMapper() {
    }

    public static GenericModelMapper singleInstance(){
        if(instance == null){
            instance = new GenericModelMapper();
        }
        return instance;
    }

    public ReservaDTO mapToReservaDTO(Reserva reserva){
        return mapper.typeMap(Reserva.class, ReservaDTO.class)
                .addMapping(src -> src.getUsuario().getUsername(), ReservaDTO::setUsername)
                .map(reserva);
    }

    public Reserva mapToReserva(ReservaDTO reservaDTO){
        return mapper.typeMap(ReservaDTO.class, Reserva.class).map(reservaDTO);
    }

    public RolDTO mapToRolDTO(Rol rol){
        return mapper.typeMap(Rol.class, RolDTO.class).map(rol);
    }

    public Rol mapToRol(RolDTO rolDTO){
        return mapper.typeMap(RolDTO.class, Rol.class).map(rolDTO);
    }

    public CategoriaDTO mapToCategoriaDTO(Categoria categoria){
        return mapper.typeMap(Categoria.class, CategoriaDTO.class).map(categoria);
    }

    public Categoria mapToCategoria(CategoriaDTO categoriaDTO){
        return mapper.typeMap(CategoriaDTO.class, Categoria.class).map(categoriaDTO);
    }

    public CaracteristicaDTO mapToCaracteristicaDTO (Caracteristica caracteristica){
        return mapper.typeMap(Caracteristica.class, CaracteristicaDTO.class).map(caracteristica);
    }

    public CiudadDTO mapToCiudadDTO(Ciudad ciudad){
        return mapper.typeMap(Ciudad.class, CiudadDTO.class).map(ciudad);
    }

    public Ciudad mapToCiudad(CiudadDTO ciudadDTO){
        return mapper.typeMap(CiudadDTO.class, Ciudad.class).map(ciudadDTO);
    }

    public ProductoDTO mapToProductoDTO(Producto producto){
        return mapper.typeMap(Producto.class, ProductoDTO.class).map(producto);
    }

    public Producto mapToProducto(ProductoDTO productoDTO){
        return mapper.typeMap(ProductoDTO.class, Producto.class).map(productoDTO);
    }

    public UsuarioDTO mapToUsuarioDTO(Usuario usuario){
        return mapper.typeMap(Usuario.class, UsuarioDTO.class).map(usuario);
    }

    public Usuario mapToUsuario(UsuarioDTO usuarioDTO){
        return mapper.typeMap(UsuarioDTO.class, Usuario.class).map(usuarioDTO);
    }

    public Usuario mapToUsuarioCreacion(UsuarioCreacionDTO usuarioCreacionDTO){
        return mapper.typeMap(UsuarioCreacionDTO.class, Usuario.class)
                .addMapping(UsuarioDTO::getRol, Usuario::setUsuarioRol)
                .map(usuarioCreacionDTO);
    }

    public Producto mapToProductoCreacion(ProductoCreacionDTO productoCreacionDTO){
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.typeMap(ProductoCreacionDTO.class, Producto.class)
                .addMappings(m -> m.skip(Producto::setProductoId))
                .implicitMappings()
                .map(productoCreacionDTO);
    }
}
