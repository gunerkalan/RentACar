package com.gunerk.rentacar.service.concrete;

import com.gunerk.rentacar.core.utilities.Results.*;
import com.gunerk.rentacar.dto.concrete.CarDTO;
import com.gunerk.rentacar.dto.concrete.CarListDto;
import com.gunerk.rentacar.entities.concrete.*;
import com.gunerk.rentacar.repository.concrete.BrandRepository;
import com.gunerk.rentacar.repository.concrete.CarRepository;
import com.gunerk.rentacar.repository.concrete.ColorRepository;
import com.gunerk.rentacar.service.abstracts.CarService;
import com.gunerk.rentacar.service.constants.Messages;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ColorRepository colorRepository;
    private final BrandRepository brandRepository;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ColorRepository colorRepository, BrandRepository brandRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.colorRepository = colorRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public DataResult<List<CarListDto>> getAll() {
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(this.carRepository.findAll(),CarListDto[].class)),Messages.carListed);
    }

    @Override
    public DataResult<CarDTO> getByCarId(Long carId) {
        return new SuccessDataResult<>(modelMapper.map(this.carRepository.getById(carId),CarDTO.class),Messages.carListedById);
    }

    @Override
    public DataResult<List<CarListDto>> getCarsByColorId(Long colorId) {
       return new SuccessDataResult<>(Arrays.asList(modelMapper.map(this.carRepository.getByColorId(colorId),CarListDto[].class)),Messages.carListedByColorId);
    }

    @Override
    public DataResult<List<CarListDto>> getCarsByBrandId(Long brandId) {
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(this.carRepository.getByBrandId(brandId),CarListDto[].class)),Messages.carListedByBrandId);
    }


    @Override
    public DataResult<List<CarDTO>> getCarsByGearType(Enum<GearType> gearTypeEnum) {
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(this.carRepository.getByGearType(gearTypeEnum.name()),CarDTO[].class)),Messages.carListedByGearType);
    }

    @Override
    public DataResult<List<CarDTO>> getCarsByEngineType(String engineType) {
        List<Car> cars = this.carRepository.getByEngineType(engineType);
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(cars,CarDTO[].class)),Messages.carListedByEngineType);
    }

    @Override
    public Result save(CarDTO carDTO) {
        Car car = setSave(carDTO);
        this.carRepository.save(car);
        return new SuccessResult(Messages.carSavedSuccecfully);
    }

    private Car setSave(CarDTO carDTO){
        Car car = modelMapper.map(carDTO,Car.class);
        Color color = colorRepository.getOne(carDTO.getColorId());
        car.setColor(color);
        Brand brand = brandRepository.getOne(carDTO.getBrandId());
        car.setBrand(brand);
        return car;
    }

    @Override
    public Result update(Long id, CarDTO carDTO) {
        Optional<Car> carIs = carRepository.findById(id);
        if(!carIs.isPresent()){
            return new ErrorResult(Messages.carErrorUpdate);
        }

        Car car = carIs.get();

        car.setCarName(carDTO.getCarName());
        car.setDailyPrice(carDTO.getDailyPrice());
        car.setDescriptions(carDTO.getDescriptions());
        car.setModelYear(carDTO.getModelYear());
        car.setEngineType(carDTO.getEngineType());
        car.setGearType(carDTO.getGearType());

        Color color = colorRepository.getOne(carDTO.getColorId());
        car.setColor(color);

        Brand brand = brandRepository.getOne(carDTO.getBrandId());
        car.setBrand(brand);
        this.carRepository.save(car);

        return new SuccessResult(Messages.carUpdateSuccess);
    }

    @Override
    public Result delete(Long id) {
        this.carRepository.deleteById(id);
        return new SuccessResult(Messages.carDeleteSuccess);
    }
}
