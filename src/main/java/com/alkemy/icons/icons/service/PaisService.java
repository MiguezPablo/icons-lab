package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.Pais;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaisService {

    List<PaisDTO> getAllPaises();

    PaisDTO save(PaisDTO paisDTO);

    Pais getEntityById(Long id);

    void deletePais(Long id);

    PaisDTO updatePais(Long id,PaisDTO pais);

    List<PaisBasicDTO> getBasicPais();

    List<PaisDTO> getPaisByFilters(String name, Long continent, String order);

    void addIcon(Long idPais, Long idIcon);

    void removeIcon(Long idPais, Long idIcon);

}
