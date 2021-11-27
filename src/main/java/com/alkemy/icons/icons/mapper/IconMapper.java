package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class IconMapper {

    @Autowired
    private PaisMapper paisMapper;

    public Icon iconDTO2Entity(IconDTO dto) {
        Icon entity = new Icon();
        entity.setImagen(dto.getImagen());
        entity.setDenominacion(dto.getDenominacion());
        entity.setFechaCreacion(
                this.string2LocalDate(dto.getFechaCreacion())
        );
        entity.setAltura(dto.getAltura());
        entity.setHistoria(dto.getHistoria());
        return entity;
    }

    public IconDTO iconEntity2DTO(Icon entity, boolean loadPaises) {
        IconDTO dto = new IconDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        dto.setFechaCreacion(entity.getFechaCreacion().toString());
        dto.setAltura(entity.getAltura());
        dto.setHistoria(entity.getHistoria());
        if (loadPaises) {
            List<PaisDTO> paisesDTO = this.paisMapper.paisEntityList2DTOList(entity.getPaises(), false);
            dto.setPaises(paisesDTO);
        }
        return dto;
    }

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public void iconEntityRefreshValues(Icon entity, IconDTO iconDTO) {
        entity.setImagen(iconDTO.getImagen());
        entity.setDenominacion(iconDTO.getDenominacion());
        entity.setFechaCreacion(
                this.string2LocalDate(iconDTO.getFechaCreacion())
        );
        entity.setAltura(iconDTO.getAltura());
        entity.setHistoria(iconDTO.getHistoria());
    }

    public Set<Icon> iconDTOList2Entity(List<IconDTO> dtos) {
        Set<Icon> entities = new HashSet<>();
        for (IconDTO dto : dtos) {
            entities.add(this.iconDTO2Entity(dto));
        }
        return entities;
    }

    /**
     * @param entities   (Set or List)
     * @param loadPaises
     */
    public List<IconDTO> iconEntitySet2DTOList(Collection<Icon> entities, boolean loadPaises) {
        List<IconDTO> dtos = new ArrayList<>();
        for (Icon entity : entities) {
            dtos.add(this.iconEntity2DTO(entity, loadPaises));
        }
        return dtos;
    }

    public List<IconBasicDTO> iconEntitySet2BasicDTOList(Collection<Icon> entities) {
        List<IconBasicDTO> dtos = new ArrayList<>();
        IconBasicDTO basicDTO;
        for (Icon entity : entities) {
            basicDTO = new IconBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setDenominacion(entity.getDenominacion());
            dtos.add(basicDTO);
        }
        return dtos;
    }
}
