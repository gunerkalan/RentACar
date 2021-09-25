package com.gunerk.rentacar.service.abstracts;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.CustomerDTO;

import java.util.List;

public interface CustomerService {

    DataResult<List<CustomerDTO>> getAll();

    DataResult<CustomerDTO> getByCustomerId(Long customerId);

    Result save(CustomerDTO customerDTO);

    Result update(Long id, CustomerDTO customerDTO);

    Result delete(Long id);

}
