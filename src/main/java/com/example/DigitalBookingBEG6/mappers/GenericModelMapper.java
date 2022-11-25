package com.example.DigitalBookingBEG6.mappers;

import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.model.dto.ReservaDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.service.impl.ReservaService;
import com.example.DigitalBookingBEG6.service.impl.UsuarioService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UsuarioDTO mapToUsuarioDTO(Usuario usuario){
        return mapper.map(usuario, UsuarioDTO.class);
    }
}
