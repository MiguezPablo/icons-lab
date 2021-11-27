package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.Icon;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IconRepository extends JpaRepository<Icon,Long> {
    List<Icon> findAll(Specification<Icon> spec);

}
