package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import com.alkemy.icons.icons.entity.Continente;

import java.util.List;

public interface ContinenteService {

    ContinenteDTO save (ContinenteDTO dto);

    List<ContinenteDTO> getAllContinentes();

}
