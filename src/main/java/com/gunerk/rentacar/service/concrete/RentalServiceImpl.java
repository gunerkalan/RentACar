package com.gunerk.rentacar.service.concrete;

import com.gunerk.rentacar.core.utilities.Results.*;
import com.gunerk.rentacar.dto.concrete.RentalDTO;
import com.gunerk.rentacar.dto.concrete.RentalDetailDTO;
import com.gunerk.rentacar.entities.concrete.Car;
import com.gunerk.rentacar.entities.concrete.Customer;
import com.gunerk.rentacar.entities.concrete.Rental;
import com.gunerk.rentacar.repository.concrete.CarRepository;
import com.gunerk.rentacar.repository.concrete.CustomerRepository;
import com.gunerk.rentacar.repository.concrete.RentalRepository;
import com.gunerk.rentacar.service.abstracts.RentalService;
import com.gunerk.rentacar.service.constants.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private final ModelMapper modelMapper;
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public RentalServiceImpl(ModelMapper modelMapper, RentalRepository rentalRepository
    ,CarRepository carRepository, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public DataResult<List<RentalDTO>> getAll() {
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(this.rentalRepository.findAll(),RentalDTO[].class)),Messages.rentalListed);
    }

    @Override
    public DataResult<RentalDTO> getByRentalId(Long rentalId) {
        return new SuccessDataResult<>(modelMapper.map(this.rentalRepository.getById(rentalId),RentalDTO.class),Messages.rentalListedById);
    }

    @Override
    public Result save(RentalDTO rentalDTO) {
        Rental rental = modelMapper.map(rentalDTO,Rental.class);

        Customer customer = customerRepository.getOne(rentalDTO.getCustomerId());
        rental.setCustomer(customer);

        Car car = carRepository.getOne(rentalDTO.getCarId());
        rental.setCarRental(car);

        return new SuccessResult(Messages.rentalSavedSuccecfully);
    }

    @Override
    public Result update(Long id, RentalDTO rentalDTO) {
        Optional<Rental> rentalIs = rentalRepository.findById(id);
        if(rentalIs.isPresent())
        {
            return new ErrorResult(Messages.rentalErrorUpdate);
        }else {
            Rental rental = rentalIs.get();
            rental.setReturnDate(rentalDTO.getReturnDate());
            rental.setRentDate(rentalDTO.getRentDate());

            Customer customer = customerRepository.getOne(rentalDTO.getCustomerId());
            rental.setCustomer(customer);

            Car car = carRepository.getOne(rentalDTO.getCarId());
            rental.setCarRental(car);

            return new SuccessResult(Messages.rentalUpdateSuccess);

        }
    }

    @Override
    public Result delete(Long id) {
        this.rentalRepository.deleteById(id);
        return new SuccessResult(Messages.rentalDeleteSuccess);
    }

    @Override
    public DataResult<List<RentalDetailDTO>> getRentalDetails(Long rentalId) {

        return null;
    }
}
