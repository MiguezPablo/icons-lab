package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContinenteService {

    ContinenteDTO save (ContinenteDTO dto);

    List<ContinenteDTO> getAllContinentes();

}
