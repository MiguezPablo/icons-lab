package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE pais SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String denominacion;

    @Column(name = "cant_habitantes")
    private Long cantidadHabitantes;

    private Long superficie;

    @ManyToOne(fetch = FetchType.EAGER) //cascade = CascadeType.ALL
    @JoinColumn(name = "continente_id")//, insertable = false, updatable = false)// nullable = false
    private Continente continente;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "icon_pais",
            joinColumns = @JoinColumn(name = "pais_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<Icon> icons = new HashSet<>();

    private boolean deleted = Boolean.FALSE;

    public void addIcon(Icon icon) {
        this.icons.add(icon);
    }

    public void removeIcon(Icon icon) {
        this.icons.remove(icon);
    }
}
