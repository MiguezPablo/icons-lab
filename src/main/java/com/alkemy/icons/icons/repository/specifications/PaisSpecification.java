package com.alkemy.icons.icons.repository.specifications;


import com.alkemy.icons.icons.dto.PaisFiltersDTO;
import com.alkemy.icons.icons.entity.Icon;
import com.alkemy.icons.icons.entity.Pais;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaisSpecification {
    public Specification<Pais> getByFilters(PaisFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("denominacion")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }



            if (StringUtils.hasLength(String.valueOf(filtersDTO.getContinent()))) {
                Join<Pais, Icon> join = root.join("paises", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(filtersDTO.getContinent()));
            }

            // Remove duplicates
            query.distinct(true);

            // Order resolver
            String orderByField = "denominacion";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
