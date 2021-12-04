package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IconService {

    IconDTO getDetailsById(Long id);

    List<IconBasicDTO> getAll();

    List<IconDTO> getAllComplete();

    List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order);

    IconDTO save(IconDTO iconDTO);

    IconDTO update(Long id, IconDTO icon);

    void delete(Long id);
}
