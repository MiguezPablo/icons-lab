package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("paises")
public class PaisController {

    private PaisService paisService;

    @Autowired
    public PaisController(PaisService paisService) {this.paisService = paisService;}

    @GetMapping
    public ResponseEntity<List<PaisDTO>> getAll() {
        List<PaisDTO> paises = this.paisService.getAllPaises();
        return ResponseEntity.ok().body(paises);
    }

    @GetMapping("/basic")
    public ResponseEntity<List<PaisBasicDTO>> getBasic() {
        List<PaisBasicDTO> paisBasicDTOList = this.paisService.getBasicPais();
        return ResponseEntity.ok().body(paisBasicDTOList);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PaisDTO>> getPaisByFilters(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) Long continente,
    @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<PaisDTO> paises = this.paisService.getPaisByFilters(name, continente, order);
        return ResponseEntity.ok(paises);
    }

    @PostMapping
    public ResponseEntity<PaisDTO> save(@RequestBody PaisDTO pais) {
        PaisDTO paisDTO = this.paisService.save(pais);
        return ResponseEntity.ok().body(paisDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.paisService.deletePais(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDTO> update(@PathVariable Long id, @RequestBody PaisDTO paisDTO) {
        PaisDTO result = this.paisService.updatePais(id, paisDTO);
        return ResponseEntity.ok().body(result);
    }
}
