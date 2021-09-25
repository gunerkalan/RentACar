package com.gunerk.rentacar.entities.concrete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gunerk.rentacar.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "color", indexes = {@Index(name="idx_colorname", columnList="color_name")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Color implements IEntity {

    @Id
    @SequenceGenerator(name="seq_color",allocationSize = 1)
    @GeneratedValue(generator = "seq_color",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="color_name",length = 100, nullable = false)
    private String colorName;

    @OneToMany(mappedBy="color")
    @JsonBackReference
    private Set<Car> cars;

}
