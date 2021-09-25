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
@Table(name = "carimage", indexes = {@Index(name="idx_imagepath", columnList="image_path")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarImage implements IEntity {

    @Id
    @SequenceGenerator(name="seq_carimage",allocationSize = 1)
    @GeneratedValue(generator = "seq_carimage",strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn(name="car_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Car car;

    @Column(name ="image_path", nullable = false)
    private String imagePath;

    @Column(name ="image_date",nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date imageDate;


}
