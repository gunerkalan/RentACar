package com.gunerk.rentacar.service.abstracts;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.CarDTO;
import com.gunerk.rentacar.dto.concrete.CarListDto;
import com.gunerk.rentacar.entities.concrete.EngineType;
import com.gunerk.rentacar.entities.concrete.GearType;

import java.util.List;

public interface CarService {

    DataResult<List<CarListDto>> getAll();

    DataResult<CarDTO> getByCarId(Long carId);

    DataResult<List<CarListDto>> getCarsByColorId(Long colorId);

    DataResult<List<CarListDto>> getCarsByBrandId(Long brandId);

    DataResult<List<CarDTO>> getCarsByGearType(Enum<GearType> gearTypeEnum);

    DataResult<List<CarDTO>> getCarsByEngineType(String engineType);

    Result save(CarDTO carDTO);

    Result update(Long id, CarDTO carDTO);

    Result delete(Long id);
}
