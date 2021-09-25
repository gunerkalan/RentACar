package com.gunerk.rentacar.entities.concrete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gunerk.rentacar.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer", indexes = {@Index(name="idx_customername", columnList="customer_name"),@Index(name = "idx_customertc",columnList = "customer_tc")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements IEntity {

    @Id
    @SequenceGenerator(name="seq_customer",allocationSize = 1)
    @GeneratedValue(generator = "seq_customer",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="customer_name",length = 150, nullable = false)
    private String customerName;

    @Column(name="customer_tc",length = 11, nullable = false, unique = true)
    private String customerTc;

    @OneToMany(mappedBy="customer")
    @JsonBackReference
    private Set<Rental> rentals;
}
