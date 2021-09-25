package com.gunerk.rentacar.api;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.CustomerDTO;
import com.gunerk.rentacar.service.abstracts.CustomerService;
import com.gunerk.rentacar.core.utilities.Paths.ApiPaths;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = ApiPaths.CustomerCtrl.CTRL)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get All Customer Operation", response = String.class, responseContainer = "List")
    public DataResult<List<CustomerDTO>> getAll(){
        return this.customerService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get By Id Operation", response = CustomerDTO.class)
    public DataResult<CustomerDTO> getById(@PathVariable(value = "id",required = true)Long id){
        return this.customerService.getByCustomerId(id);
    }

    @PostMapping
    @ApiOperation(value = "Add Operation", response = CustomerDTO.class)
    public Result addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return this.customerService.save(customerDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = CustomerDTO.class)
    public Result updateCustomer(@PathVariable(value = "id",required = true)Long id, @Valid @RequestBody CustomerDTO customerDTO)
    {
        return this.customerService.update(id,customerDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public Result deleteCustomer(@PathVariable(value = "id",required = true)Long id){
        return this.customerService.delete(id);
    }
}
