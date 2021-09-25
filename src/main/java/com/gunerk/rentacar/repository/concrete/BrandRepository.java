package com.gunerk.rentacar.repository.concrete;

import com.gunerk.rentacar.entities.concrete.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand getById(Long id);
}
