package com.alkemy.icons.icons.repository;


import com.alkemy.icons.icons.entity.Pais;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    List<Pais> findAll(Specification<Pais> spec);
}
