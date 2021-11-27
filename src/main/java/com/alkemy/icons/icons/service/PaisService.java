package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.Pais;

import java.util.List;

public interface PaisService {

    List<PaisDTO> getAllPaises();

    PaisDTO save(PaisDTO paisDTO);

    Pais getEntityById(Long id);

    void deletePais(Long id);

    PaisDTO updatePais(Long id,PaisDTO pais);

    List<PaisBasicDTO> getBasicPais();

    List<PaisDTO> getPaisByFilters(String name, Long continent, String order);
}
