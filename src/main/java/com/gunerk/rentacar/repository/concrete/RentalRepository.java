package com.gunerk.rentacar.repository.concrete;

import com.gunerk.rentacar.entities.concrete.Brand;
import com.gunerk.rentacar.entities.concrete.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long> {

    Rental getById(Long id);
}
