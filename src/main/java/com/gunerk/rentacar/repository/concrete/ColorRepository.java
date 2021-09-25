package com.gunerk.rentacar.repository.concrete;

import com.gunerk.rentacar.entities.concrete.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Long> {

    Color getById(Long id);
}
