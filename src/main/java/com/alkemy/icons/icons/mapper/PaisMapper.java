package com.alkemy.icons.icons.mapper;


import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.Continente;
import com.alkemy.icons.icons.entity.Icon;
import com.alkemy.icons.icons.entity.Pais;
import com.alkemy.icons.icons.repository.ContinenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class PaisMapper {

    @Autowired
    private IconMapper iconMapper;
    @Autowired
    private ContinenteMapper continenteMapper;
    @Autowired
    private ContinenteRepository continenteRepository;

    public Pais paisDTO2Entity(PaisDTO dto) {
        Continente conti = continenteRepository.findById(dto.getContinenteId()).orElseThrow();
        Pais entity = new Pais();
        entity.setImagen(dto.getImagen());
        entity.setDenominacion(dto.getDenominacion());
        entity.setCantidadHabitantes(dto.getCantidadHabitantes());
        entity.setSuperficie(dto.getSuperficie());
        entity.setContinente(conti);
        Set<Icon> icons = this.iconMapper.iconDTOList2Entity(dto.getIcons());
        entity.setIcons(icons);
        return entity;
    }

    public PaisDTO paisEntity2DTO(Pais entity, boolean loadIcons) {
        PaisDTO dto = new PaisDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        dto.setCantidadHabitantes(entity.getCantidadHabitantes());
        dto.setContinenteId(entity.getContinente().getId());
        dto.setSuperficie(entity.getSuperficie());
        if(loadIcons) {
            List<IconDTO> iconDTOS = this.iconMapper.iconEntitySet2DTOList(entity.getIcons(), false);
            dto.setIcons(iconDTOS);
        }
        return dto;
    }

    public List<PaisDTO> paisEntityList2DTOList(List<Pais> entities, boolean loadIcons) {
        List<PaisDTO> dtos = new ArrayList<>();
        for (Pais entity : entities) {
            dtos.add(this.paisEntity2DTO(entity, loadIcons));
        }
        return dtos;
    }

    public void PaisEntityRefreshValues(Pais entity, PaisDTO paisDTO) {
        entity.setImagen(paisDTO.getImagen());
        entity.setDenominacion(paisDTO.getDenominacion());
        entity.setCantidadHabitantes(paisDTO.getCantidadHabitantes());
        entity.setSuperficie(paisDTO.getSuperficie());
        Continente conti = continenteRepository.getById(paisDTO.getContinenteId());
        entity.setContinente(conti);
        Set<Icon> icons = this.iconMapper.iconDTOList2Entity(paisDTO.getIcons());
        entity.setIcons(icons);
    }

    public List<Pais> paisDTOList2Entity(List<PaisDTO> dtos) {
        List<Pais> entities = new ArrayList<>();
        for (PaisDTO dto : dtos) {
            entities.add(this.paisDTO2Entity(dto));
        }
        return entities;
    }

    public List<PaisBasicDTO> paisEntitySet2BasicDTOList(Collection<Pais> entities) {
        List<PaisBasicDTO> dtos = new ArrayList<>();
        PaisBasicDTO basicDTO;
        for (Pais entity : entities) {
            basicDTO = new PaisBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setDenominacion(entity.getDenominacion());
            basicDTO.setCantidadHabitantes(entity.getCantidadHabitantes());
            dtos.add(basicDTO);
        }
        return dtos;
    }

}
