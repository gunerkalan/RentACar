package com.gunerk.rentacar.service.concrete;

import com.gunerk.rentacar.core.utilities.Results.*;
import com.gunerk.rentacar.dto.concrete.CustomerDTO;
import com.gunerk.rentacar.entities.concrete.Customer;
import com.gunerk.rentacar.entities.concrete.Tc;
import com.gunerk.rentacar.repository.concrete.CustomerRepository;
import com.gunerk.rentacar.service.abstracts.CustomerService;
import com.gunerk.rentacar.service.constants.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<CustomerDTO>> getAll() {
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(this.customerRepository.findAll(),CustomerDTO[].class)),Messages.customerListed);
    }

    @Override
    public DataResult<CustomerDTO> getByCustomerId(Long customerId) {
        return new SuccessDataResult<>(modelMapper.map(this.customerRepository.getById(customerId),CustomerDTO.class),Messages.customerListedById);
    }


    @Override
    public Result save(CustomerDTO customerDTO) {
        Boolean resultCheck =checkCustomerTc(customerDTO.getCustomerTc(), null,Tc.Tcsiz);
        if(resultCheck){
            this.customerRepository.save(modelMapper.map(customerDTO,Customer.class));
            return new SuccessResult(Messages.customerSavedSuccecfully);
        }else{
            return new ErrorResult(Messages.customerTcError);
        }
    }

    private Boolean checkCustomerTc(String Tc, Optional<Long> id, Enum<Tc> tcEnum){
        if(tcEnum.name()=="Tcsiz"){
            Optional<Customer> customerDb = Optional.ofNullable(customerRepository.getByCustomerTc(Tc));
            if(customerDb.isEmpty())
               return false;
            else
               return true;
        }else{
            Optional<Customer> customerCheck = Optional.ofNullable(customerRepository.getByCustomerTcAndIdNot(Tc, id.get()));
            if(customerCheck.isEmpty())
               return false;
            else
                return true;
        }
    }

    @Override
    public Result update(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerIs = customerRepository.findById(id);
        if(!customerIs.isPresent()){
            return new ErrorResult(Messages.customerErrorUpdate);
        }
        Boolean resultCheck =checkCustomerTc(customerDTO.getCustomerTc(), null,Tc.Tcli);
        if(resultCheck==false){
            return new ErrorDataResult<>(Messages.customerTcError);
        }else{
          Customer customerDb = customerIs.get();
          customerDb.setCustomerName(customerDTO.getCustomerName());
          customerDb.setCustomerTc(customerDTO.getCustomerTc());
          this.customerRepository.save(customerDb);
          return new SuccessResult(Messages.customerSavedSuccecfully);
        }
    }

    @Override
    public Result delete(Long id) {
        this.customerRepository.deleteById(id);
        return new SuccessResult(Messages.customerDeleteSuccess);
    }
}
