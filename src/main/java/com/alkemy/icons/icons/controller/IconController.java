package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/icons")
public class IconController {

    @Autowired
    private IconService iconService;

    @GetMapping("/basic")
    public ResponseEntity<List<IconBasicDTO>> getAll() {
        List<IconBasicDTO> icons = this.iconService.getAll();
        return ResponseEntity.ok(icons);
    }

    @GetMapping("/all")
    public ResponseEntity<List<IconDTO>> getAllComplete() {
        List<IconDTO> icons = this.iconService.getAllComplete();
        return ResponseEntity.ok(icons);
    }


    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getDetailsById(@PathVariable Long id) {
        IconDTO icon = this.iconService.getDetailsById(id);
        return ResponseEntity.ok(icon);
    }

    @GetMapping
    public ResponseEntity<List<IconDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<IconDTO> icons = this.iconService.getByFilters(name, date, cities, order);
        return ResponseEntity.ok(icons);
    }

    @PostMapping
    public ResponseEntity<IconDTO> save(@RequestBody IconDTO icon) {
        IconDTO result = this.iconService.save(icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IconDTO> update(@PathVariable Long id, @RequestBody IconDTO icon) {
        IconDTO result = this.iconService.update(id, icon);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.iconService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
