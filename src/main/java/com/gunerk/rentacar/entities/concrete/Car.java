package com.gunerk.rentacar.entities.concrete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gunerk.rentacar.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car", indexes = {@Index(name="idx_carname", columnList="car_name")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements IEntity {

    @Id
    @SequenceGenerator(name="seq_car",allocationSize = 1)
    @GeneratedValue(generator = "seq_car",strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn(name="brand_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Brand brand;

    @JoinColumn(name="color_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Color color;

    @Column(name ="engine_type")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name ="gear_type")
    @Enumerated(EnumType.STRING)
    private GearType gearType;

    @Column(name="car_name",length = 150, nullable = false)
    private String carName;

    @Column(name="model_year", nullable = false)
    private int modelYear;

    @Column(name="daily_price", nullable = false)
    private Float dailyPrice;

    @Column(name = "descriptions", columnDefinition = "TEXT")
    private String descriptions;

    @OneToMany(mappedBy="car")
    @JsonBackReference
    private Set<CarImage> carImages;

    @OneToMany(mappedBy="carRental")
    @JsonBackReference
    private Set<Rental> rentals;

}
