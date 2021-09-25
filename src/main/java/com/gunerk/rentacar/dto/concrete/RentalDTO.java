package com.gunerk.rentacar.dto.concrete;


import com.gunerk.rentacar.dto.abstracts.IDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Rental Data Transfer Object")
public class RentalDTO implements IDto {

    @ApiModelProperty(required = true,value = "Rental ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Rent Date")
    private Date rentDate;

    @NotNull
    @ApiModelProperty(required = true,value = "Return Date")
    private Date returnDate;

    @NotNull
    @ApiModelProperty(required = true,value = "Car ID")
    private Long carId;

    @NotNull
    @ApiModelProperty(required = true,value = "Customer ID")
    private Long customerId;

    @ApiModelProperty(required = true,value = "Rental Car")
    private CarDTO car;

    @ApiModelProperty(required = true,value = "Rental Customer")
    private CustomerDTO customer;
}
