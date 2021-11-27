package com.alkemy.icons.icons.service.impl;


import com.alkemy.icons.icons.dto.*;
import com.alkemy.icons.icons.entity.Pais;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.PaisMapper;
import com.alkemy.icons.icons.repository.PaisRepository;
import com.alkemy.icons.icons.repository.specifications.PaisSpecification;
import com.alkemy.icons.icons.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PaisServiceImpl implements PaisService {

    private PaisRepository paisRepository;
    private PaisMapper paisMapper;
    private PaisSpecification paisSpecification;

    @Autowired
    public PaisServiceImpl(PaisRepository paisRepository, PaisMapper paisMapper, PaisSpecification paisSpecification) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
        this.paisSpecification = paisSpecification;
    }


    public PaisDTO save(PaisDTO paisDTO) {
        Pais paisEntity = this.paisMapper.paisDTO2Entity(paisDTO);
        Pais entitySaved = this.paisRepository.save(paisEntity);
        PaisDTO result = this.paisMapper.paisEntity2DTO(entitySaved, true);
        return result;
    }

    public List<PaisDTO> getAllPaises() {
        List<Pais> entities = this.paisRepository.findAll();
        List<PaisDTO> result = this.paisMapper.paisEntityList2DTOList(entities, true);
        return result;
    }

    public List<PaisBasicDTO> getBasicPais() {
        List<Pais> entities = this.paisRepository.findAll();
        List<PaisBasicDTO> result = this.paisMapper.paisEntitySet2BasicDTOList(entities);
        return result;
    }

    public Pais getEntityById(Long id) {
        return this.paisRepository.getById(id);
    }

    public void deletePais (Long id) {
        this.paisRepository.deleteById(id);
    }

    public PaisDTO updatePais (Long id, PaisDTO paisDTO) {
        Optional<Pais> entity = this.paisRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id pais no valido");
        }
        this.paisMapper.PaisEntityRefreshValues(entity.get(), paisDTO);
        Pais entitySaved = this.paisRepository.save(entity.get());
        PaisDTO result = this.paisMapper.paisEntity2DTO(entitySaved, false);
        return result;
    }

    public List<PaisDTO> getPaisByFilters(String name, Long continent, String order) {
        PaisFiltersDTO filtersDTO = new PaisFiltersDTO(name, continent, order);
        List<Pais> entities = this.paisRepository.findAll(
                this.paisSpecification.getByFilters(filtersDTO)
        );
        List<PaisDTO> paisDTOS = this.paisMapper.paisEntityList2DTOList(entities, true);
        return paisDTOS;
    }


}
