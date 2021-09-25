package com.gunerk.rentacar.repository.concrete;

import com.gunerk.rentacar.entities.concrete.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    Car getById(Long id);

    List<Car> getByBrandId(Long id);

    List<Car> getByColorId(Long id);

    List<Car> getByGearType(String gearType);

    List<Car> getByEngineType(String engineType);
}
