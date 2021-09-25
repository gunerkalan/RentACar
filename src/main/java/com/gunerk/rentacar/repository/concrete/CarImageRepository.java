package com.gunerk.rentacar.repository.concrete;

import com.gunerk.rentacar.entities.concrete.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}
