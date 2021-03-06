package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.IconFiltersDTO;
import com.alkemy.icons.icons.entity.Icon;
import com.alkemy.icons.icons.entity.Pais;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.repository.IconRepository;
import com.alkemy.icons.icons.repository.specifications.IconSpecification;
import com.alkemy.icons.icons.service.IconService;
import com.alkemy.icons.icons.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IconServiceImpl implements IconService {

    @Autowired
    private IconRepository iconRepository;
    @Autowired
    private IconSpecification iconSpecification;
    @Autowired
    private IconMapper iconMapper;


    @Override
    public IconDTO getDetailsById(Long id) {
        Optional<Icon> entity = this.iconRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id icono no valido");
        }
        IconDTO iconDTO = this.iconMapper.iconEntity2DTO(entity.get(), true);
        return iconDTO;
    }

    @Override
    public List<IconBasicDTO> getAll() {
        List<Icon> entities = this.iconRepository.findAll();
        List<IconBasicDTO> iconBasicDTOS = this.iconMapper.iconEntitySet2BasicDTOList(entities);
        return iconBasicDTOS;
    }

    @Override
    public List<IconDTO> getAllComplete() {
        List<Icon> entities = this.iconRepository.findAll();
        List<IconDTO> iconDTOS = this.iconMapper.iconEntitySet2DTOList(entities, true);
        return iconDTOS;
    }

    @Override
    public List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        IconFiltersDTO filtersDTO = new IconFiltersDTO(name, date, cities, order);
        List<Icon> entities = this.iconRepository.findAll(
                this.iconSpecification.getByFilters(filtersDTO)
        );
        List<IconDTO> iconDTOS = this.iconMapper.iconEntitySet2DTOList(entities, true);
        return iconDTOS;
    }

    @Override
    public IconDTO save (IconDTO iconDTO) {
        Icon entity = this.iconMapper.iconDTO2Entity(iconDTO);
        Icon entitySaved = this.iconRepository.save(entity);
        IconDTO result = this.iconMapper.iconEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public IconDTO update(Long id, IconDTO iconDTO) {
        Optional<Icon> entity = this.iconRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id icono no valido");
        }
        this.iconMapper.iconEntityRefreshValues(entity.get(), iconDTO);
        Icon entitySaved = this.iconRepository.save(entity.get());
        IconDTO result = this.iconMapper.iconEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.iconRepository.deleteById(id);
    }
}
