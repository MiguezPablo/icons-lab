package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import com.alkemy.icons.icons.entity.Continente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinenteMapper {

    public Continente continenteDTO2Entity(ContinenteDTO dto){
        Continente continenteEntity = new Continente();
        continenteEntity.setImagen(dto.getImagen());
        continenteEntity.setDenominacion(dto.getDenominacion());
        return continenteEntity;
    }

    public ContinenteDTO continenteEntity2DTO(Continente entity){
        ContinenteDTO dto = new ContinenteDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        return dto;
    }

    public List<ContinenteDTO> continenteEntityList2DTOList(List<Continente> entities) {
        List<ContinenteDTO> dtos = new ArrayList<>();
        for (Continente entity:entities) {
            dtos.add(this.continenteEntity2DTO(entity));
        }
        return dtos;
    }



}
