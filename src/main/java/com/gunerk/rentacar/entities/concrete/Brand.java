package com.gunerk.rentacar.entities.concrete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gunerk.rentacar.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "brand", indexes = {@Index(name="idx_brandname", columnList="brand_name")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements IEntity {

    @Id
    @SequenceGenerator(name="seq_brand",allocationSize = 1)
    @GeneratedValue(generator = "seq_brand",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="brand_name",length = 100, nullable = false)
    private String brandName;

    @OneToMany(mappedBy="brand")
    @JsonBackReference
    private Set<Car> cars;
}
