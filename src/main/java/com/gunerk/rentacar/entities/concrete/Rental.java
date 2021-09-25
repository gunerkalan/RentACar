package com.gunerk.rentacar.entities.concrete;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gunerk.rentacar.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rental", indexes = {@Index(name="idx_rentalcustomer", columnList="customer_id")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental implements IEntity {

    @Id
    @SequenceGenerator(name="seq_rental",allocationSize = 1)
    @GeneratedValue(generator = "seq_rental",strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn(name="car_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Car carRental;

    @JoinColumn(name="customer_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Customer customer;

    @Column(name ="rent_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date rentDate;

    @Column(name ="return_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date returnDate;
}
