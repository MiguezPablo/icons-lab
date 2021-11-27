package com.alkemy.icons.icons.dto;

import com.alkemy.icons.icons.entity.Continente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaisDTO {
    private Long id;
    private String imagen;
    private String denominacion;
    private Long cantidadHabitantes;
    private Long superficie;
    //private Long continenteId;
    private Continente continente;
    private List<IconDTO> icons;
}
