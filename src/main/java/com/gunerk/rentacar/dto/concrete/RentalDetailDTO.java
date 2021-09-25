package com.gunerk.rentacar.dto.concrete;

import com.gunerk.rentacar.dto.abstracts.IDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Rental Detail Data Transfer Object")
public class RentalDetailDTO implements IDto {

    @ApiModelProperty(required = true,value = "Rental ID")
    private Long id;

    @ApiModelProperty(required = true,value = "Rent Date")
    private Date rentDate;

    @ApiModelProperty(required = true,value = "Return Date")
    private Date returnDate;

    @ApiModelProperty(required = true,value = "Car Brand Name")
    private String brandName;

    @ApiModelProperty(required = true,value = "Name")
    private String name;
}
